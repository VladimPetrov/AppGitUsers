package ru.gb.appgitusers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.appgitusers.app
import ru.gb.appgitusers.databinding.ActivityMainBinding
import ru.gb.appgitusers.domain.GitUserEntity
import ru.gb.appgitusers.domain.IGitUserRepository

class MainActivity : AppCompatActivity(),GitUsersContract.View {
    private lateinit var binding: ActivityMainBinding
    private val adapter = GitUserAdapter()
    private lateinit var presenter:GitUsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initPresenter()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initPresenter() {
        presenter = GitUsersPresenter(app.userRepo)
        presenter.attach(this)
    }

    private fun initView(){
        initRecycleView()
        binding.activityMainFab.setOnClickListener {
            presenter.onRefreshData()
        }
        showProgress(false)
    }
    private fun initRecycleView() {
        binding.activityMainRecycler.layoutManager = LinearLayoutManager(this)
        binding.activityMainRecycler.adapter = adapter
    }

    override fun showUsers(users:List<GitUserEntity>){
        adapter.dataSet(users)
    }
    override fun showError(throwable:Throwable){
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
    override fun showProgress(show: Boolean){
        binding.activityMainProgressBar.isVisible = show
        binding.activityMainRecycler.isVisible = !show
    }
}