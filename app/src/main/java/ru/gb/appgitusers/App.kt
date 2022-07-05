package ru.gb.appgitusers

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.data.UserRepo
import ru.gb.appgitusers.data.retrofit.GitUsersApi
import ru.gb.appgitusers.data.room.GitUserDao
import ru.gb.appgitusers.data.room.GitUserDataBase
import ru.gb.appgitusers.domain.IGitUserRepository


class App : Application() {
    private val baseUrl = "https://api.github.com/"
    private val nameDb = "GitUsers.db"
    private val datasource by lazy {
        Room.databaseBuilder(
            this.applicationContext,
            GitUserDataBase::class.java,
            nameDb
        ).build().gitUserDao()
    }
    private val api by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
                .build()
            )
            .build()
            .create(GitUsersApi::class.java)
    }

    val userRepo: IGitUserRepository by lazy {
        //LocalGitUserRepository()
        //ApiGitUserRepository()
        UserRepo(api,datasource)
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App