package ru.gb.appgitusers.data

import ru.gb.appgitusers.data.retrofit.ApiGitUserRepository
import ru.gb.appgitusers.data.retrofit.GitUsersApi
import ru.gb.appgitusers.data.room.GitUserDao
import ru.gb.appgitusers.data.room.RoomGitUserRepository
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository

class UserRepo(
    private val api: GitUsersApi,
    private val datasource: GitUserDao
) : IGitUserRepository {

    private val internetRepo: IGitUserRepository by lazy { ApiGitUserRepository(api) }
    private val cacheRepo by lazy { RoomGitUserRepository(datasource) }

    override fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        internetRepo.loadUsers(
            onSuccess = {
                onSuccess(it)
                cacheRepo.addUsers(it)
            },
            onError = {
                onError?.invoke(it)
                cacheRepo.loadUsers(onSuccess = {
                    onSuccess(it)
                })
            }
        )
    }

    override fun loadUserDetails(
        userName: String,
        onSuccess: (GitUserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        internetRepo.loadUserDetails(userName,
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError?.invoke(it)
                cacheRepo.loadUserDetails(userName,
                    onSuccess = {
                        onSuccess(it)
                    })
            }
        )
    }
}