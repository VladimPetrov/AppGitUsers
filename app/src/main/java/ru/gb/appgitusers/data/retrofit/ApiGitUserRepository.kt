package ru.gb.appgitusers.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository


private const val BASE_URL = "https://api.github.com/"

class ApiGitUserRepository() : IGitUserRepository {

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
        .create(GitUsersApi::class.java)


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

    override fun loadUserDetails(
        userName: String,
        onSuccess: (GitUserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        val callback = object : Callback<GitUserEntity> {
            override fun onResponse(
                call: Call<GitUserEntity>,
                response: Response<GitUserEntity>
            ) {
                val serverResponse: GitUserEntity? = response.body()
                if (response.isSuccessful && serverResponse != null) {
                    onSuccess(serverResponse)
                }
            }

            override fun onFailure(call: Call<GitUserEntity>, t: Throwable) {
                onError?.invoke(t)
            }
        }
        api.loadUserDetails(userName).enqueue(callback)
    }
}