package co.ke.srklagat.gitissuetracker.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IssueDao : CoroutineBaseDao<IssueEntity> {

    @Query("SELECT * FROM issues")
    fun getAllIssues(): Flow<List<IssueEntity>>

    @Query("SELECT * FROM issues WHERE id = :id")
    suspend fun getIssueById(id: Int): IssueEntity?

    @Query("DELETE FROM issues")
    suspend fun clearIssues()
}

