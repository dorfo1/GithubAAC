package rodolfo.com.br.githubaac.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rodolfo.com.br.githubaac.ui.main.MainActivity

@Module
abstract class ActivityModule{

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun ccontributeMainActivity(): MainActivity
}