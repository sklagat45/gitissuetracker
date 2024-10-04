package co.ke.srklagat.gitissuetracker.data.local.datasorces


import androidx.room.Database
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.ke.srklagat.gitissuetracker.data.local.dao.IssueDao
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity


@Database(
    entities = [IssueEntity::class],
    version = 3

)
@RewriteQueriesToDropUnusedColumns
@TypeConverters(Converters::class)
abstract class IssueDatabase : RoomDatabase() {
    abstract val issueDao: IssueDao

    companion object{
        const val DATABASE_NAME= "gitIssue.db"
    }
}