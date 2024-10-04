package co.ke.srklagat.gitissuetracker.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import co.ke.srklagat.gitissuetracker.ui.composable.IssueCard

@Composable
fun IssueList(issues: List<IssueEntity>) {
    LazyColumn {
        items(issues) { issue ->
            IssueCard(
                issueTitle = issue.title,
                dateOpened = "Date Not Provided",
                issueNumber = "",
                author = "",
                comments = 0
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
