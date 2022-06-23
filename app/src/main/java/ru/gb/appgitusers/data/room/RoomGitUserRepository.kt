package ru.gb.appgitusers.data.room

import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.utils.converterListGitUserEntityToListRoom
import ru.gb.appgitusers.utils.converterListRoomToListGitUserEntity
import ru.gb.appgitusers.utils.converterRoomToGitUserEntity

class RoomGitUserRepository(private val datasource: GitUserDao) {

    fun loadUsers() = (converterListRoomToListGitUserEntity(datasource.getAll()))

    fun loadUserDetails(userName: String) =
        (converterRoomToGitUserEntity(datasource.getUser(userName)))

    fun addUsers(usersList: List<GitUserEntity>) {
        datasource.deleteAll()
        datasource.addAll(converterListGitUserEntityToListRoom(usersList))
    }
}