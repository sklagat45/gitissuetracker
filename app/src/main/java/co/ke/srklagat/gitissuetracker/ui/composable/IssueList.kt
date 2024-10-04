package co.ke.srklagat.gitissuetracker.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity

@Composable
fun IssueList(issues: List<IssueEntity>) {
    LazyColumn(
        modifier = Modifier
            .background(Color(0xFFECF3F9))
            .padding(3.dp)
    ) {
        items(issues.take(10)) { issue ->
            IssueCard(
                issue = issue,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}
