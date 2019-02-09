package rodolfo.com.br.githubaac.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import rodolfo.com.br.githubaac.data.local.converter.DateConverter
import rodolfo.com.br.githubaac.data.local.dao.UserDAO
import rodolfo.com.br.githubaac.data.local.entity.User

@Database(entities = [User::class],version = 1)
@TypeConverters(DateConverter::class)
abstract class MeuBancoDeDados : RoomDatabase() {

    abstract fun userDao():UserDAO

    companion object {
        private val INSTANCE : MeuBancoDeDados? = null
    }
}