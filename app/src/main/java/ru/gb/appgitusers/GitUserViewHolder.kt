package ru.gb.appgitusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.appgitusers.databinding.ItemUserBinding
import ru.gb.appgitusers.domain.GitUserEntity

class GitUserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    val binding = ItemUserBinding.bind(itemView)
    fun bind(item: GitUserEntity) {
        binding.itemUserId.text = item.id
        binding.itemUserName.text = item.login

    }
}