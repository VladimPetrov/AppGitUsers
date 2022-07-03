package ru.gb.appgitusers.domain

interface IGitUserRepository {
    fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun loadUserDetails(
        userName : String,
        onSuccess: (GitUserEntity) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}