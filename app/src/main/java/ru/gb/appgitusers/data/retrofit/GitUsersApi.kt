package ru.gb.appgitusers.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gb.appgitusers.domain.GitUserEntity

interface GitUsersApi {

    @GET("/users")
    fun loadUsers(): Call<List<GitUserEntity>>

    @GET("/users/{userName}")
    fun loadUserDetails(@Path("userName") userName:String): Call<GitUserEntity>
}