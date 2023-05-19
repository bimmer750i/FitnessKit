package fitness.test.kit

import android.app.Application
import fitness.test.kit.presentation.di.AppComponent
import fitness.test.kit.presentation.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .build()
    }

}