package ru.gb.appgitusers

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.dil.DependenciesHolder
import ru.gb.appgitusers.di.Module


class App : Application() {
    val di by lazy {
        DependenciesHolder().apply {
            Module(this@App.applicationContext, this)
        }
    }


}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App