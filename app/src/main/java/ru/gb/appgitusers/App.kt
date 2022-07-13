package ru.gb.appgitusers

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.data.UserRepo
import ru.gb.appgitusers.data.retrofit.GitUsersApi
import ru.gb.appgitusers.data.room.GitUserDao
import ru.gb.appgitusers.data.room.GitUserDataBase
import ru.gb.appgitusers.di.Module
import ru.gb.appgitusers.domain.IGitUserRepository


class App : Application() {
  val di by lazy { Module(this.applicationContext) }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App