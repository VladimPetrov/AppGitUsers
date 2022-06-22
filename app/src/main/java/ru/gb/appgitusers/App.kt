package ru.gb.appgitusers

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.appgitusers.data.LocalGitUserRepository
import ru.gb.appgitusers.data.retrofit.ApiGitUserRepository
import ru.gb.appgitusers.domain.IGitUserRepository

class App : Application() {
    val userRepo: IGitUserRepository by lazy {
        //LocalGitUserRepository()
        ApiGitUserRepository()
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App