package ru.gb.appgitusers.domain

import com.google.gson.annotations.SerializedName

data class GitUserEntity(
    val login: String,
    val id: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)