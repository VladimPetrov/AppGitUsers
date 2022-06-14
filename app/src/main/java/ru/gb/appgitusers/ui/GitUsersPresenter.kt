package ru.gb.appgitusers.ui

import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository

class GitUsersPresenter(val gitUserRepository: IGitUserRepository):GitUsersContract.Presenter {
    private var view:GitUsersContract.View? = null
    private var listUsers:List<GitUserEntity>? = null
    private var inProgress = false

    override fun attach(view: GitUsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        listUsers?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefreshData() {
        inProgress = true
        view?.showProgress(inProgress)

        gitUserRepository.loadUsers({
            view?.showUsers(it)
            listUsers = it
            inProgress = false
            view?.showProgress(inProgress)
        },{
            view?.showError(it)
            inProgress = false
            view?.showProgress(inProgress)
        }
        )
    }
}