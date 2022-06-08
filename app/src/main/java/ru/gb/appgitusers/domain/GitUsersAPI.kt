package ru.gb.appgitusers.domain

import retrofit2.Call
import retrofit2.http.GET

interface GitUsersAPI {

    @GET("/users")
    fun loadUsers(): Call<List<GitUserEntity>>
}