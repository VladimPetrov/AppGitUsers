package ru.gb.appgitusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository
import ru.gb.appgitusers.utils.SingleEventLiveData

class GitUserViewModel(val gitUserRepository: IGitUserRepository) : ViewModel() {
    val userListLiveData: LiveData<List<GitUserEntity>> = MutableLiveData()
    val progressLiveData: LiveData<Boolean> = MutableLiveData()
    val errorLiveData: LiveData<Throwable> = SingleEventLiveData<Throwable>()
    val userDetailsLiveData: LiveData<GitUserEntity> = MutableLiveData()

    fun onRefresh() {
        loadData()
    }

    fun onOpenUserDetails(pos: Int) {
        userDetailsLiveData.mutable().postValue(userListLiveData.mutable().let {
            it.value?.get(pos) ?: null
        })
    }

    fun onCloseUserDetails() {
        userDetailsLiveData.mutable().postValue(null)
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


    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }
}