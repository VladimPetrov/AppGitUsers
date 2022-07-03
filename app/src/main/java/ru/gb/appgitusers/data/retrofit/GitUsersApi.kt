package ru.gb.appgitusers.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gb.appgitusers.domain.GitUserEntity

interface GitUsersApi {

    @GET("/users")
    fun loadUsers(): Single<List<GitUserEntity>>

    @GET("/users/{userName}")
    fun loadUserDetails(@Path("userName") userName:String): Single<GitUserEntity>
}