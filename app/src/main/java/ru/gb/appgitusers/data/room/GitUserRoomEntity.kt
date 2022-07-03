package ru.gb.appgitusers.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitUserRoomEntity(
    @PrimaryKey
    val id: String,
    val login: String,
    val avatarUrl: String,
    val name: String,
    val blog: String,
    val location: String?,
    val email: String?,
    val bio: String?,
)
