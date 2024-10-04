package co.ke.srklagat.gitissuetracker.data.repository

import co.ke.srklagat.gitissuetracker.data.local.dao.IssueDao
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import co.ke.srklagat.gitissuetracker.data.remote.GraphQLService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import timber.log.Timber

class IssueRepositoryImpl(
    private val issueDao: IssueDao,
    private val graphQLService: GraphQLService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IssueRepository {
    override suspend fun fetchIssues() = withContext(ioDispatcher) {
        val query = """
    {
      repository(owner: "sklagat45", name: "cashBackTestApp") {
        issues(last: 20) {
          edges {
            node {
              title
              url
              state
              createdAt
              body
              assignees(first: 5) {
                edges {
                  node {
                    name
                  }
                }
              }
              comments(last: 5) {
                edges {
                  node {
                    bodyText
                  }
                }
              }
            }
          }
        }
      }
    }
    """.trimIndent()

        val responseJson = graphQLService.fetchIssues(query)

        // Map the fetched data into `IssueEntity` objects
        val fetchedIssues = responseJson?.let { json ->
            val jsonObject = Json.parseToJsonElement(json).jsonObject
            jsonObject["data"]?.jsonObject?.get("repository")?.jsonObject?.get("issues")?.jsonObject?.get(
                "edges"
            )?.jsonArray?.map {
                val node = it.jsonObject["node"]?.jsonObject
                val assignees = node?.get("assignees")?.jsonObject?.get("edges")?.jsonArray?.joinToString(", ") { assigneeEdge ->
                    assigneeEdge.jsonObject["node"]?.jsonObject?.get("name")?.toString()?.trim('"') ?: "Unassigned"
                } ?: "Unassigned"

                val comments = node?.get("comments")?.jsonObject?.get("edges")?.jsonArray?.joinToString("\n") { commentEdge ->
                    commentEdge.jsonObject["node"]?.jsonObject?.get("bodyText")?.toString()?.trim('"') ?: ""
                } ?: "No comments"

                IssueEntity(
                    id = 0,
                    title = node?.get("title")?.toString()?.trim('"') ?: "",
                    url = node?.get("url")?.toString()?.trim('"') ?: "",
                    labels = node?.get("labels")?.jsonObject?.get("edges")?.jsonArray?.joinToString(", ") { labelEdge ->
                        labelEdge.jsonObject["node"]?.jsonObject?.get("name")?.toString()?.trim('"') ?: ""
                    } ?: "",
                    createdAt = node?.get("createdAt")?.toString()?.trim('"') ?: "",
                    assignedTo = assignees,
                    status = node?.get("state")?.toString()?.trim('"') ?: "",
                    description = node?.get("body")?.toString()?.trim('"') ?: "",
                    comments = comments
                )
            } ?: emptyList()
        } ?: emptyList()

        Timber.d("Fetched Issues: $fetchedIssues")
        issueDao.insertAsync(fetchedIssues)
    }


    override fun getLocalIssues(): Flow<List<IssueEntity>> {
        return issueDao.getAllIssues()
    }
}
