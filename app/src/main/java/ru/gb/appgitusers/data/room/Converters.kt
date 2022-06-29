package ru.gb.appgitusers.utils

import ru.gb.appgitusers.data.room.GitUserRoomEntity
import ru.gb.appgitusers.domain.GitUserEntity

fun converterRoomToGitUserEntity(userRoom: GitUserRoomEntity) =
    GitUserEntity(
        login = userRoom.login,
        id = userRoom.id,
        avatarUrl = userRoom.avatarUrl,
        name = userRoom.name ?: "",
        blog = userRoom.blog ?: "",
        location = userRoom.location ?: "",
        email = userRoom.email ?: "",
        bio = userRoom.bio ?: ""
    )

fun converterGitUserEntityToRoom(user: GitUserEntity) =
    GitUserRoomEntity(
        login = user.login,
        id = user.id,
        avatarUrl = user.avatarUrl,
        name = user.name ?: "",
        blog = user.blog ?: "",
        location = user.location ?: "",
        email = user.email ?: "",
        bio = user.bio ?: ""
    )

fun converterListRoomToListGitUserEntity(listRoomUsers: List<GitUserRoomEntity>): List<GitUserEntity> {
    val listUser: MutableList<GitUserEntity> = mutableListOf()
    for (userRoom in listRoomUsers) {
        listUser.add(converterRoomToGitUserEntity(userRoom))
    }
    return listUser
}

fun converterListGitUserEntityToListRoom(listUsers: List<GitUserEntity>): List<GitUserRoomEntity> {
    val listUser: MutableList<GitUserRoomEntity> = mutableListOf()
    for (user in listUsers) {
        listUser.add(converterGitUserEntityToRoom(user))
    }
    return listUser
}