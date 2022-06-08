package ru.gb.appgitusers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gb.appgitusers.R
import ru.gb.appgitusers.databinding.ItemUserBinding
import ru.gb.appgitusers.domain.GitUserEntity

class GitUserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    val binding = ItemUserBinding.bind(itemView)
    fun bind(item: GitUserEntity) {
        binding.itemUserId.text = item.id
        binding.itemUserName.text = item.login
        binding.itemUserImage.load(item.url)
    }
}