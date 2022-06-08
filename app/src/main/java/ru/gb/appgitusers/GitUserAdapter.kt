package ru.gb.appgitusers

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.appgitusers.domain.GitUserEntity

class GitUserAdapter:RecyclerView.Adapter<GitUserViewHolder>() {
    private val data = mutableListOf<GitUserEntity>()
    fun dataSet(list:List<GitUserEntity>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GitUserViewHolder(parent)

    override fun onBindViewHolder(holder: GitUserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}