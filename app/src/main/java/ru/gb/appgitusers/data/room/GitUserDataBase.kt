package ru.gb.appgitusers.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GitUserRoomEntity::class], version = 1, exportSchema = false)
abstract class GitUserDataBase : RoomDatabase() {
    abstract fun gitUserDao(): GitUserDao
}