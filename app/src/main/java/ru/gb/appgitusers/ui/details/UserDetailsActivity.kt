package ru.gb.appgitusers.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import coil.load
import ru.gb.appgitusers.R
import ru.gb.appgitusers.databinding.ActivityUserDetailsBinding
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.ui.GitUserAdapter

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.getParcelable<GitUserEntity>(BUNDLE_USER_DETAILS)?.let {
            initView(it)
        }

    }

    private fun initView(gitUserEntity: GitUserEntity) {
        with(binding) {
            activityUserDetailsName.text = gitUserEntity.name
            activityUserDetailsLogin.text = "(${gitUserEntity.login})"
            activityUserDetailsImg.load(gitUserEntity.avatarUrl)
            gitUserEntity.location?.let {
                activityUserDetailsLocation.text = "Location:${it}"
            }
            gitUserEntity.blog?.let {
                activityUserDetailsBlog.text = Html.fromHtml("<u>${it}</u>")
            }
            gitUserEntity.email?.let {
                activityUserDetailsEmail.text = it
            }
            gitUserEntity.bio?.let {
                activityUserDetailsBio.text = it
            }
        }
    }

    companion object {
        const val BUNDLE_USER_DETAILS = "GitUserEntity"
    }

}