package rodolfo.com.br.githubaac

import android.app.Activity
import android.app.Application
import dagger.Module
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import rodolfo.com.br.githubaac.di.components.DaggerAppComponent
import rodolfo.com.br.githubaac.di.modules.ViewModelModule
import javax.inject.Inject


@Module
class MyApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        this.initDagger()
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    private fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}
