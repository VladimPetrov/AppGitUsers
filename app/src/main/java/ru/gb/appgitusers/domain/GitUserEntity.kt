package ru.gb.appgitusers.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class GitUserEntity(
    val login: String,
    val id: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String,
    val blog: String,
    val location: String?,
    val email: String?,
    val bio: String?,
) : Parcelable