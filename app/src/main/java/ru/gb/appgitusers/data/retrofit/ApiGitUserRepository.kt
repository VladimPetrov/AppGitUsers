package ru.gb.appgitusers.data.retrofit

import io.reactivex.rxjava3.kotlin.subscribeBy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository


private const val BASE_URL = "https://api.github.com/"

class ApiGitUserRepository() : IGitUserRepository {

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .build()
        )
        .build()
        .create(GitUsersApi::class.java)


    override fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.loadUsers().subscribeBy(
            onSuccess = { onSuccess(it) },
            onError = { onError?.invoke(it)}
        )
    }

    override fun loadUserDetails(
        userName: String,
        onSuccess: (GitUserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {

        api.loadUserDetails(userName).subscribeBy(
            onSuccess = { onSuccess(it) },
            onError = { onError?.invoke(it)}
        )
    }
}