package co.ke.srklagat.gitissuetracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import co.ke.srklagat.gitissuetracker.ui.composable.IssueTrackerHome
import co.ke.srklagat.gitissuetracker.ui.theme.GitIssueTrackerTheme
import co.ke.srklagat.gitissuetracker.ui.viewmodel.IssuesViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitIssueTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    val viewModel: IssuesViewModel = getViewModel()
                    val issuesFlow = viewModel.issues
                    IssueTrackerHome(issueFlow = issuesFlow)
                }
            }
        }
    }
}

