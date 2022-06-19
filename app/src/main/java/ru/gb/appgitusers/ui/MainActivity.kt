package ru.gb.appgitusers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import ru.gb.appgitusers.app
import ru.gb.appgitusers.databinding.ActivityMainBinding
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.ui.presenter.GitUsersContract
import ru.gb.appgitusers.ui.presenter.GitUsersPresenter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = GitUserAdapter({
        gitUserViewModel.onOpenUserDetails(it)
    })
    private lateinit var gitUserViewModel: GitUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        gitUserViewModel = extractViewModel()
        gitUserViewModel.progressLiveData.observe(this) { showProgress(it) }
        gitUserViewModel.userListLiveData.observe(this) { showUsers(it) }
        gitUserViewModel.errorLiveData.observe(this) { showError(it) }
        gitUserViewModel.userDetailsLiveData.observe(this) {
            if (it == null) {
                showDetails(false)
            } else {
                showDetails(true)
                showUsersDetail(it)
            }
        }
    }

    private fun extractViewModel() = lastCustomNonConfigurationInstance as? GitUserViewModel
        ?: GitUserViewModel(app.userRepo)


    private fun initView() {
        initRecycleView()
        binding.activityMainFab.setOnClickListener {
            gitUserViewModel.onRefresh()
        }
        binding.activityMainDetailsView.setOnClickListener {
            gitUserViewModel.onCloseUserDetails()
        }
        showProgress(false)
    }

    private fun initRecycleView() {
        binding.activityMainRecycler.layoutManager = LinearLayoutManager(this)
        binding.activityMainRecycler.adapter = adapter
    }

    override fun onRetainCustomNonConfigurationInstance(): GitUserViewModel = gitUserViewModel

    private fun showUsers(users: List<GitUserEntity>) {
        adapter.dataSet(users)
    }

    private fun showUsersDetail(user: GitUserEntity) {
        binding.activityMainTitle.text = user.login
        binding.activityMainImg.load(user.avatarUrl)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(show: Boolean) {
        binding.activityMainProgressBar.isVisible = show
        binding.activityMainRecycler.isVisible = !show
    }

    private fun showDetails(show: Boolean) {
        binding.activityMainDetailsView.isVisible = show
        binding.activityMainRecycler.isVisible = !show
        binding.activityMainFab.isVisible = !show
    }
}