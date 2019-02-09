package rodolfo.com.br.githubaac.data.repositories

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rodolfo.com.br.githubaac.data.local.dao.UserDAO
import rodolfo.com.br.githubaac.data.local.entity.User
import rodolfo.com.br.githubaac.data.remote.UserWebService
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor(private val webservice: UserWebService, private val userDao: UserDAO, private val executor: Executor) {

    fun getUser(userLogin: String): LiveData<User> {
        refreshUser(userLogin)
        return userDao.load(userLogin)
    }

    private fun refreshUser(userLogin: String) {
        executor.execute {

            val userExists = userDao.hasUser(userLogin, getMaxRefreshTime(Date())) != null

            if (!userExists) {
                webservice.getUser(userLogin).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        executor.execute {
                            val user = response.body()
                            user?.lastRefresh = Date()
                            if (user != null)
                                userDao.save(user)
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {}
                })
            }
        }
    }

    private fun getMaxRefreshTime(currentDate: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = currentDate
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES)
        return cal.time
    }

    companion object {

        private const val FRESH_TIMEOUT_IN_MINUTES = 3
    }
}

