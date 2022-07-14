package ru.gb.appgitusers.data

import android.content.Context
import ru.gb.appgitusers.data.retrofit.ApiGitUserRepository
import ru.gb.appgitusers.data.room.RoomGitUserRepository
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository

class UserRepo(context: Context) : IGitUserRepository {
    private val internetRepo: IGitUserRepository by lazy { ApiGitUserRepository() }
    private val cacheRepo by lazy { RoomGitUserRepository(context) }


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