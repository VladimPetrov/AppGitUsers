package ru.gb.appgitusers.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.ui.GitUserAdapter


private const val BASE_URL = "https://api.github.com/"

class APIGitUserRepository() : IGitUserRepository {

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


    override fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        val callback = object : Callback<List<GitUserEntity>> {
            override fun onResponse(
                call: Call<List<GitUserEntity>>,
                response: Response<List<GitUserEntity>>
            ) {
                val serverResponse: List<GitUserEntity>? = response.body()
                if (response.isSuccessful && serverResponse != null) {
                    onSuccess(serverResponse)
                }
            }

            override fun onFailure(call: Call<List<GitUserEntity>>, t: Throwable) {
                onError?.invoke(t)
            }
        }
        api.loadUsers().enqueue(callback)
    }
}