package ru.gb.appgitusers.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.GitUsersAPI
import ru.gb.appgitusers.domain.IGitUserRepository
import ru.gb.appgitusers.ui.GitUserAdapter




class APIGitUserRepository(private val api: GitUsersAPI) : IGitUserRepository {

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