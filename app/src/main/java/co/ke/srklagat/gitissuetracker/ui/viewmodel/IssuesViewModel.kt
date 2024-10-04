package co.ke.srklagat.gitissuetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import co.ke.srklagat.gitissuetracker.data.repository.IssueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class IssuesViewModel(
    private val issueRepository: IssueRepository
) : ViewModel() {

    private val _issues = MutableStateFlow<List<IssueEntity>>(emptyList())
    val issues: StateFlow<List<IssueEntity>> get() = _issues

    init {
        fetchRemoteIssues()
        getLocalIssues()
    }

    private fun fetchRemoteIssues() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                issueRepository.fetchIssues()
                getLocalIssues()
            } catch (e: Exception) {
                Timber.e("Error fetching remote issues: ${e.message}")
            }
        }
    }

    private fun getLocalIssues() {
        viewModelScope.launch {
            issueRepository.getLocalIssues().collect { localIssues ->
                _issues.value = localIssues
            }
        }
    }
}
