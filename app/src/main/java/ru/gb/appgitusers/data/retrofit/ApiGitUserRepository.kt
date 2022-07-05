package ru.gb.appgitusers.data.retrofit

import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository

class ApiGitUserRepository(private val api: GitUsersApi) : IGitUserRepository {

    override fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.loadUsers().subscribeBy(
            onSuccess = { onSuccess(it) },
            onError = { onError?.invoke(it) }
        )
    }

    override fun loadUserDetails(
        userName: String,
        onSuccess: (GitUserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.loadUserDetails(userName).subscribeBy(
            onSuccess = { onSuccess(it) },
            onError = { onError?.invoke(it) }
        )
    }
}