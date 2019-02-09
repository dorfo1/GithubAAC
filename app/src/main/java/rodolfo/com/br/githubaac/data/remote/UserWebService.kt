package rodolfo.com.br.githubaac.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import rodolfo.com.br.githubaac.data.local.entity.User

interface UserWebService {

    @GET("users/{user}")
    fun getUser(@Path("user")userId:String):Call<User>
}