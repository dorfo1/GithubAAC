package rodolfo.com.br.githubaac.ui.userprofile


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user_profile.*

import rodolfo.com.br.githubaac.R
import rodolfo.com.br.githubaac.util.viewmodel.FactoryViewModel
import javax.inject.Inject


class UserProfileFragment : Fragment() {

   @Inject
   lateinit var viewModelFactory: FactoryViewModel
   private lateinit var viewModel: UserProfileViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupDagger()
        setupViewModel()
        setupView()
    }

    fun setupDagger(){
        AndroidSupportInjection.inject(this)
    }


    fun setupView(){
        btn_pesquisar.setOnClickListener{
            viewModel.getUser(et_usuario.text.toString())
            viewModel.user.observe(this, Observer {
                tv_usuario.text = it?.name
                Picasso.get().load(it?.avatarURL).into(iv_git)

            })
        }
    }

    fun setupViewModel(){
        viewModel = ViewModelProviders
                .of(this,viewModelFactory)
                .get(UserProfileViewModel::class.java)
    }


}
