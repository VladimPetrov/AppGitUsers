package ru.gb.appgitusers

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Room
import ru.gb.appgitusers.data.LocalGitUserRepository
import ru.gb.appgitusers.data.retrofit.ApiGitUserRepository
import ru.gb.appgitusers.data.room.GitUserDao
import ru.gb.appgitusers.data.room.GitUserDataBase
import ru.gb.appgitusers.domain.IGitUserRepository

class App : Application() {

    val userRepo: IGitUserRepository by lazy {
        //LocalGitUserRepository()
        ApiGitUserRepository()
    }
        private val DB_NAME = "GitUsers.db"
        private var db:GitUserDataBase? = null
        fun getGitUserDao(context: Context):GitUserDao {
            if (db == null) {
                synchronized(GitUserDataBase::class.java) {
                    db = Room.databaseBuilder(
                        context,
                        GitUserDataBase::class.java,
                        DB_NAME
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return db!!.gitUserDao()
        }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App