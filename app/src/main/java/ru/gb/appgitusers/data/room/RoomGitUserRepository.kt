package ru.gb.appgitusers.data.room

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.room.Room
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository
import ru.gb.appgitusers.utils.converterListGitUserEntityToListRoom
import ru.gb.appgitusers.utils.converterListRoomToListGitUserEntity
import ru.gb.appgitusers.utils.converterRoomToGitUserEntity

class RoomGitUserRepository(contex:Context):IGitUserRepository {
    private val DB_NAME = "GitUsers.db"
    private val datasource: GitUserDao = Room.databaseBuilder(contex,
        GitUserDataBase::class.java,
        DB_NAME).build().gitUserDao()

    override fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        onSuccess(converterListRoomToListGitUserEntity(datasource.getAll()))
    }

    override fun loadUserDetails(
        userName: String,
        onSuccess: (GitUserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        onSuccess(converterRoomToGitUserEntity(datasource.getUser(userName)))
    }

    fun addUsers(usersList: List<GitUserEntity>) {
        datasource.deleteAll()
        datasource.addAll(converterListGitUserEntityToListRoom(usersList))
    }
}