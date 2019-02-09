package rodolfo.com.br.githubaac.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rodolfo.com.br.githubaac.ui.userprofile.UserProfileFragment

@Module
abstract class  FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeUserProfileFragment(): UserProfileFragment
}