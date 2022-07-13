package ru.gb.appgitusers.di

import android.content.Context
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.data.UserRepo
import ru.gb.appgitusers.data.retrofit.GitUsersApi
import ru.gb.appgitusers.data.room.GitUserDataBase
import ru.gb.appgitusers.domain.IGitUserRepository
import ru.gb.dil.DependenciesHolder

class Module(contex: Context, diHolder: DependenciesHolder) {
    private val baseUrl = "https://api.github.com/"
    private val nameDb = "GitUsers.db"
    private val datasource by lazy {
        Room.databaseBuilder(
            contex,
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
        UserRepo(api, datasource)
    }

    init {
        diHolder.add(UserRepo::class, userRepo)
    }

}