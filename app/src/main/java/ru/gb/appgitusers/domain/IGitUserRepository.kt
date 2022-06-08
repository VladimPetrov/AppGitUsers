package ru.gb.appgitusers.domain

interface IGitUserRepository {
    fun loadUsers():List<GitUserEntity>
}