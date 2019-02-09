package rodolfo.com.br.githubaac.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import rodolfo.com.br.githubaac.R
import rodolfo.com.br.githubaac.ui.userprofile.UserProfileFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDagger()
        setupFragment()
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector(): AndroidInjector<android.support.v4.app.Fragment> {
        return dispatchingAndroidInjector
    }


    private fun setupDagger(){
        AndroidInjection.inject(this)
    }

    fun setupFragment(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container,UserProfileFragment(),null)
                .commit()
    }
}
