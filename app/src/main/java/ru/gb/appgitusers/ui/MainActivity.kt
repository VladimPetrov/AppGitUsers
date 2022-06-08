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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = GitUserAdapter()
    private val gitUserRepository: IGitUserRepository by lazy { app.userRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        initRecycleView()
        binding.activityMainFab.setOnClickListener {
            loadUsers()
        }
        showProgress(false)
    }
    private fun initRecycleView() {
        binding.activityMainRecycler.layoutManager = LinearLayoutManager(this)
        binding.activityMainRecycler.adapter = adapter
    }

    private fun loadUsers(){
        showProgress(true)
        gitUserRepository.loadUsers({
            onDataLoaded(it)
            showProgress(false)
        },{
            onError(it)
            showProgress(false)
        }
        )
    }
    private fun onDataLoaded(data:List<GitUserEntity>){
        adapter.dataSet(data)
    }
    private fun onError(throwable:Throwable){
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
    private fun showProgress(show: Boolean){
        binding.activityMainProgressBar.isVisible = show
        binding.activityMainRecycler.isVisible = !show
    }
}