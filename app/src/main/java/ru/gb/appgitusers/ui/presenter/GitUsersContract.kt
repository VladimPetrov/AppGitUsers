package ru.gb.appgitusers.ui.presenter

import ru.gb.appgitusers.domain.GitUserEntity

interface GitUsersContract {

    interface View {
        fun showUsers(users :List<GitUserEntity>)
        fun showUsersDetail(user:GitUserEntity)
        fun showError(throwable: Throwable)
        fun showProgress(show: Boolean)
        fun showDetails(show: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefreshData()
        fun onShowDetails(pos:Int)
        fun onCloseDetails()
    }
}