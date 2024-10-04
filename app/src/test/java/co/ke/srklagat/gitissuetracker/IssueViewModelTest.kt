package co.ke.srklagat.gitissuetracker.ui.viewmodel

import co.ke.srklagat.gitissuetracker.data.local.models.IssueEntity
import co.ke.srklagat.gitissuetracker.data.repository.IssueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class IssuesViewModelTest {

    private lateinit var issueRepository: IssueRepository
    private lateinit var viewModel: IssuesViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        issueRepository = mock(IssueRepository::class.java)

        Dispatchers.setMain(testDispatcher)

        viewModel = IssuesViewModel(issueRepository)
    }

    @Test
    fun `test getLocalIssues updates issues StateFlow`() = runTest {
        val mockIssues = listOf(
            IssueEntity(1, "Title 1", "http://example.com", "Label 1", "2024-10-04T00:00:00Z", "Assignee 1", "Open", "Description 1", "Comment 1"),
            IssueEntity(2, "Title 2", "http://example2.com", "Label 2", "2024-10-05T00:00:00Z", "Assignee 2", "Closed", "Description 2", "Comment 2")
        )
        `when`(issueRepository.getLocalIssues()).thenReturn(flowOf(mockIssues))

        viewModel.getLocalIssues()

        val actualIssues = viewModel.issues.value
        assertEquals(mockIssues, actualIssues)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
