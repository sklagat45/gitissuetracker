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

class IssueRepositoryImpl(
    private val issueDao: IssueDao,
    private val graphQLService: GraphQLService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IssueRepository {

    override suspend fun fetchIssues() = withContext(ioDispatcher) {
        val query = """
        {
          repository(owner: "sklagat45", name: "cashBackTestApp") {
            issues(last: 20, states: OPEN) {
              edges {
                node {
                  title
                  url
                  labels(first: 10) {
                    edges {
                      node {
                        name
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

        val fetchedIssues = responseJson?.let { json ->
            val jsonObject = Json.parseToJsonElement(json).jsonObject
            jsonObject["data"]?.jsonObject?.get("repository")?.jsonObject?.get("issues")?.jsonObject?.get(
                "edges"
            )?.jsonArray?.map {
                val node = it.jsonObject["node"]?.jsonObject
                IssueEntity(
                    id = 0,
                    title = node?.get("title")?.toString()?.trim('"') ?: "",
                    url = node?.get("url")?.toString()?.trim('"') ?: "",
                    labels = node?.get("labels")?.jsonObject?.get("edges")?.jsonArray?.joinToString(
                        ", "
                    ) { labelEdge ->
                        labelEdge.jsonObject["node"]?.jsonObject?.get("name")?.toString()?.trim('"')
                            ?: ""
                    } ?: ""
                )
            } ?: emptyList()
        } ?: emptyList()

        issueDao.insertAsync(fetchedIssues)
    }


    override fun getLocalIssues(): Flow<List<IssueEntity>> {
        return issueDao.getAllIssues()
    }
}