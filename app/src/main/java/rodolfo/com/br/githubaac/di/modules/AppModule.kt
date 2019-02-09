package rodolfo.com.br.githubaac.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rodolfo.com.br.githubaac.data.local.MeuBancoDeDados
import rodolfo.com.br.githubaac.data.local.dao.UserDAO
import rodolfo.com.br.githubaac.data.remote.UserWebService
import rodolfo.com.br.githubaac.data.repositories.UserRepository
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context:Context): MeuBancoDeDados{
          return Room.databaseBuilder(context,MeuBancoDeDados::class.java,"github.db").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database : MeuBancoDeDados):UserDAO{
        return database.userDao()
    }

    @Provides
    @Singleton
    fun providerExecutor(): Executor{
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideGson() : Gson{
        return GsonBuilder().create()
    }


    @Provides
    @Singleton
    fun providesRetrofit(gson : Gson) : Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://api.github.com")
                .build()
    }


    @Provides
    @Singleton
    fun providesUserWebService(retrofit: Retrofit) : UserWebService{
        return retrofit.create(UserWebService::class.java)
    }

    @Provides
    @Singleton
    fun providesUserRepository(webService: UserWebService,dao: UserDAO,executor: Executor) : UserRepository{
        return UserRepository(webService,dao,executor)
    }


}