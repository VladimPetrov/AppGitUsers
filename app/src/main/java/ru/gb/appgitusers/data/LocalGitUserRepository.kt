package ru.gb.appgitusers.data

import android.os.Handler
import android.os.Looper
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.GitUsersAPI
import ru.gb.appgitusers.domain.IGitUserRepository

private const val LOAD_DATA_DELAY = 3_000L

class LocalGitUserRepository(private val api: GitUsersAPI): IGitUserRepository {

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