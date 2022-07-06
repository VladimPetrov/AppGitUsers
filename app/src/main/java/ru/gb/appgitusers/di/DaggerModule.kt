package ru.gb.appgitusers.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.appgitusers.data.UserRepo
import ru.gb.appgitusers.data.retrofit.GitUsersApi
import ru.gb.appgitusers.data.room.GitUserDao
import ru.gb.appgitusers.data.room.GitUserDataBase
import ru.gb.appgitusers.domain.IGitUserRepository
import javax.inject.Singleton

@Module
class DaggerModule(private val context: Context) {
    private val baseUrl = "https://api.github.com/"
    private val nameDb = "GitUsers.db"

    @Singleton
    @Provides
    fun provideGitUserDao(): GitUserDao {
        return Room.databaseBuilder(
            context,
            GitUserDataBase::class.java,
            nameDb
        ).build().gitUserDao()
    }

    @Singleton
    @Provides
    fun provideGitUsersApi(): GitUsersApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
                .build()
            )
            .build()
            .create(GitUsersApi::class.java)
    }

    @Singleton
    @Provides
    fun provideIGitUserRepository(api: GitUsersApi, datasource: GitUserDao): IGitUserRepository {
        return UserRepo(api, datasource)
    }
}