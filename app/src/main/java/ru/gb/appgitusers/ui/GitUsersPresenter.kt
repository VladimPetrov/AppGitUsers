package ru.gb.appgitusers.ui

import ru.gb.appgitusers.domain.IGitUserRepository

class GitUsersPresenter(val gitUserRepository: IGitUserRepository):GitUsersContract.Presenter {
    private var view:GitUsersContract.View? = null

    override fun attach(view: GitUsersContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefreshData() {
        view?.showProgress(true)
        gitUserRepository.loadUsers({
            view?.showUsers(it)
            view?.showProgress(false)
        },{
            view?.showError(it)
            view?.showProgress(false)
        }
        )
    }
}