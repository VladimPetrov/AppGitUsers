package ru.gb.appgitusers.data.room

import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository
import ru.gb.appgitusers.utils.converterListGitUserEntityToListRoom
import ru.gb.appgitusers.utils.converterListRoomToListGitUserEntity
import ru.gb.appgitusers.utils.converterRoomToGitUserEntity

class RoomGitUserRepository(private val datasource: GitUserDao):IGitUserRepository {

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