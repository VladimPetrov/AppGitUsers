package ru.gb.appgitusers

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.appgitusers.di.AppComponent
import ru.gb.appgitusers.di.DaggerAppComponent
import ru.gb.appgitusers.di.DaggerModule


class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .daggerModule(DaggerModule(this@App))
            .build()
    }

//    override fun onCreate() {
//        super.onCreate()
//        startKoin{
//            androidLogger()
//            androidContext(this@App)
//            modules(appModule)
//        }
//    }

}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App