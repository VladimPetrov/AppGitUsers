package ru.gb.appgitusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.appgitusers.data.room.RoomGitUserRepository
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository
import ru.gb.appgitusers.utils.SingleEventLiveData

class GitUserViewModel(
    private val gitUserRepository: IGitUserRepository
) : ViewModel() {
    val userListLiveData: LiveData<List<GitUserEntity>> = MutableLiveData()
    val progressLiveData: LiveData<Boolean> = MutableLiveData()
    val errorLiveData: LiveData<Throwable> = SingleEventLiveData<Throwable>()
    val userDetailsLiveData: LiveData<GitUserEntity> = SingleEventLiveData<GitUserEntity>()

    fun onRefresh() {
        loadData()
    }

    fun onOpenUserDetails(gitUserEntity: GitUserEntity) {
        loadUserDetails(gitUserEntity)
    }

    private fun loadData() {
        progressLiveData.mutable().postValue(true)
        gitUserRepository.loadUsers(onSuccess = {
            progressLiveData.mutable().postValue(false)
            userListLiveData.mutable().postValue(it)
        }, onError = {
            progressLiveData.mutable().postValue(false)
            errorLiveData.mutable().postValue(it)
        })
    }

    private fun loadUserDetails(userEntity: GitUserEntity) {
        progressLiveData.mutable().postValue(true)
        gitUserRepository.loadUserDetails(userEntity.login,
            onSuccess = {
                progressLiveData.mutable().postValue(false)
                userDetailsLiveData.mutable().postValue(it)
            }, onError = {
                progressLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            })
    }


    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }
}