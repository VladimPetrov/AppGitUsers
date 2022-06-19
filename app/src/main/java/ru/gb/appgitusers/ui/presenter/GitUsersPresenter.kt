package ru.gb.appgitusers.ui.presenter

import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository

class GitUsersPresenter(val gitUserRepository: IGitUserRepository) : GitUsersContract.Presenter {
    private var view: GitUsersContract.View? = null
    private var listUsers: List<GitUserEntity>? = null
    private var positionUser: Int? = null
    private var inProgress = false
    private var inDetails = false

    override fun attach(view: GitUsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        listUsers?.let { view.showUsers(it) }
        view?.showDetails(inDetails)
        positionUser?.let {
            listUsers?.let { list ->
                view?.showUsersDetail(list[it])
            }
        }
    }

    override fun detach() {
        view = null
    }

    override fun onRefreshData() {
        inProgress = true
        view?.showProgress(inProgress)

        gitUserRepository.loadUsers(onSuccess = {
            view?.showUsers(it)
            listUsers = it
            inProgress = false
            view?.showProgress(inProgress)
        }, onError = {
            view?.showError(it)
            inProgress = false
            view?.showProgress(inProgress)
        }
        )
    }

    override fun onShowDetails(pos: Int) {
        inDetails = true
        positionUser = pos
        view?.showDetails(true)
        listUsers?.let { view?.showUsersDetail(it[pos]) }
    }

    override fun onCloseDetails() {
        inDetails = false
        positionUser = null
        view?.showDetails(inDetails)
    }
}