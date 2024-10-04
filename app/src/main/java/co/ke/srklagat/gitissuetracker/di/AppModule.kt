package co.ke.srklagat.gitissuetracker.di

import androidx.room.Room
import co.ke.srklagat.gitissuetracker.data.local.datasorces.IssueDatabase
import co.ke.srklagat.gitissuetracker.data.remote.GraphQLService
import co.ke.srklagat.gitissuetracker.data.repository.IssueRepository
import co.ke.srklagat.gitissuetracker.data.repository.IssueRepositoryImpl
import co.ke.srklagat.gitissuetracker.ui.viewmodel.IssuesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            IssueDatabase::class.java,
            IssueDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { get<IssueDatabase>().issueDao }
    single { GraphQLService }

    factory { Dispatchers.IO }

    factory<IssueRepository> {
        IssueRepositoryImpl(
            issueDao = get(),
            graphQLService = get(),
            ioDispatcher = get()
        )
    }

    viewModel {
        IssuesViewModel(issueRepository = get())
    }
}
