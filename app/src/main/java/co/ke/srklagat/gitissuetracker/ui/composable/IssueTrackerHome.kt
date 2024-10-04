package co.ke.srklagat.gitissuetracker.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import kotlinx.coroutines.flow.Flow

@Composable
fun IssueTrackerHome(issueFlow: Flow<List<IssueEntity>>) {
    val issues by issueFlow.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe6eef2)) // Set background color here
            .padding(8.dp)
    ) {
        TopSection()
        SearchBar()
        FilterSection()
        IssueList(issues)
    }
}

