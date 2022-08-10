package ru.gb.appgitusers

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.data.APIGitUserRepository
import ru.gb.appgitusers.data.LocalGitUserRepository
import ru.gb.appgitusers.domain.GitUsersAPI
import ru.gb.appgitusers.domain.IGitUserRepository

private const val BASE_URL = "https://api.github.com/"

class App : Application() {

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .build()
        )
        .build()
        .create(GitUsersAPI::class.java)

    val userRepo: IGitUserRepository by lazy {
        //LocalGitUserRepository(api)
        APIGitUserRepository(api)
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App