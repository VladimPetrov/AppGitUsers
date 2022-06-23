package ru.gb.appgitusers.data.room

import androidx.room.*
import ru.gb.appgitusers.domain.GitUserEntity

@Dao
interface GitUserDao {
    @Query("SELECT * FROM GitUserRoomEntity")
    fun getAll(): List<GitUserRoomEntity>

    @Query("SELECT * FROM GitUserRoomEntity WHERE login=:login")
    fun getUser(login: String): GitUserRoomEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAll(gitUsersRoomEntity: List<GitUserRoomEntity>)

    @Query("DELETE FROM GitUserRoomEntity")
    fun deleteAll()
}