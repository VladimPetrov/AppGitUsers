package ru.gb.appgitusers.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.appgitusers.domain.GitUserEntity

class GitUserAdapter(val onClickItem: ((gitUserEntity: GitUserEntity) -> Unit)? = null) :
    RecyclerView.Adapter<GitUserViewHolder>() {
    private val data = mutableListOf<GitUserEntity>()
    fun dataSet(list: List<GitUserEntity>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GitUserViewHolder(parent,onClickItem)

    override fun onBindViewHolder(holder: GitUserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}