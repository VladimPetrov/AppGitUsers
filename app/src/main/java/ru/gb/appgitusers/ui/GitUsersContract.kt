package ru.gb.appgitusers.ui

import ru.gb.appgitusers.domain.GitUserEntity

interface GitUsersContract {

    interface View {
        fun showUsers(users :List<GitUserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(show: Boolean)
    }

    interface Presenter {
        fun attach(view:View)
        fun detach()
        fun onRefreshData()
    }
}