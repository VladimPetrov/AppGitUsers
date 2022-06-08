package ru.gb.appgitusers.domain

import android.os.Handler
import android.os.Looper

private const val LOAD_DATA_DELAY = 3_000L

class LocalGitUserRepository:IGitUserRepository {

    private val data: List<GitUserEntity> = listOf(
        GitUserEntity("mojombo", "1", "https://avatars.githubusercontent.com/u/1?v=4"),
        GitUserEntity("defunkt", "2", "https://avatars.githubusercontent.com/u/2?v=4"),
        GitUserEntity("pjhyett", "3", "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    override fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        }, LOAD_DATA_DELAY)
    }
}