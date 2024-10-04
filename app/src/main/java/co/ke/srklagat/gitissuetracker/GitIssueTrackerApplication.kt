package co.ke.srklagat.gitissuetracker

import android.app.Application
import co.ke.srklagat.gitissuetracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.error.KoinApplicationAlreadyStartedException
import timber.log.Timber

class GitIssueTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initKoin() {
        try {
            startKoin {
                androidContext(this@GitIssueTrackerApplication)
                modules(appModule)
            }
        } catch (error: KoinApplicationAlreadyStartedException) {
            Timber.e("Koin already started: ${error.localizedMessage}")
        }
    }

    private fun initTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }
}
