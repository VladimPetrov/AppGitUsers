package ru.gb.appgitusers.data

import android.os.Handler
import android.os.Looper
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository

private const val LOAD_DATA_DELAY = 3_000L

class LocalGitUserRepository: IGitUserRepository {

    private val listGitUsersEntity: List<GitUserEntity> = listOf(
        GitUserEntity("mojombo", "1", "https://avatars.githubusercontent.com/u/1?v=4",
            "Tom Preston-Werner","http://tom.preston-werner.com","San Francisco","tom@mojombo.com",null),
        GitUserEntity("defunkt", "2", "https://avatars.githubusercontent.com/u/2?v=4",
        "Chris Wanstrath","http://chriswanstrath.com/",null,null,null),
        GitUserEntity("pjhyett", "3", "https://avatars.githubusercontent.com/u/3?v=4",
        "PJ Hyett","https://hyett.com","San Francisco","pj@hyett.com",null),
    )

    override fun loadUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(listGitUsersEntity)
        }, LOAD_DATA_DELAY)
    }

    override fun loadUserDetails(
        userName:String,
        onSuccess: (GitUserEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(getUserByName(userName))
        }, LOAD_DATA_DELAY)
    }

    private fun getUserByName(userName: String):GitUserEntity{
        for (item in listGitUsersEntity) {
            if (item.name.equals(userName)) return item
        }
        return listGitUsersEntity[0]
    }
}