package co.ke.srklagat.gitissuetracker.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issues")
data class IssueEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val url: String,
    val labels: String,
    val createdAt: String,
    val assignedTo: String,
    val status: String,
    val description: String,
    val comments: String
)

