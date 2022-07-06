package ru.gb.appgitusers.di

import android.os.Handler
import android.os.Looper
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.data.UserRepo
import ru.gb.appgitusers.data.retrofit.GitUsersApi
import ru.gb.appgitusers.data.room.GitUserDao
import ru.gb.appgitusers.data.room.GitUserDataBase
import ru.gb.appgitusers.domain.IGitUserRepository
import ru.gb.appgitusers.ui.GitUserViewModel

val appModule = module {
    single(named("baseUrl")) { "https://api.github.com/" }
    single(named("nameDb")) {"GitUsers.db" }
    single<GitUserDao> {
        Room.databaseBuilder(
            androidContext(),
            GitUserDataBase::class.java,
            get(named("nameDb"))
        ).build().gitUserDao()
    }
    single<GitUsersApi> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(get<String>(named("baseUrl")))
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
    single<Handler> { Handler(Looper.getMainLooper()) }

    single<IGitUserRepository> {
        //LocalGitUserRepository()
        //ApiGitUserRepository()
        UserRepo(get(), get())
    }
   viewModel { GitUserViewModel(get()) }

}