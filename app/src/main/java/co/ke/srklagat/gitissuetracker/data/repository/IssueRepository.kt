package co.ke.srklagat.gitissuetracker.data.repository

import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import kotlinx.coroutines.flow.Flow

interface IssueRepository {
    suspend fun fetchIssues()
    fun getLocalIssues(): Flow<List<IssueEntity>>

}
