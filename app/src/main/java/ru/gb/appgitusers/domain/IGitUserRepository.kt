package ru.gb.appgitusers.domain

interface IGitUserRepository {
    fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}