package ru.gb.appgitusers.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitUserEntity(
    val login: String,
    val id: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
) : Parcelable