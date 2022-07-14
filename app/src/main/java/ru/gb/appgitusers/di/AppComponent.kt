package ru.gb.appgitusers.di

import dagger.Component
import ru.gb.appgitusers.domain.IGitUserRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [DaggerModule::class])
interface AppComponent {
    fun getUserRepository(): IGitUserRepository
}