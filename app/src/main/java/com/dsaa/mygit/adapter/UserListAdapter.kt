package com.dsaa.mygit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsaa.mygit.databinding.ItemUserBinding
import com.dsaa.mygit.model.UserListItem
import javax.inject.Inject

class UserListAdapter : PagingDataAdapter<UserListItem, UserListAdapter.UserViewHolder>(COMPARATOR) {
    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<UserListItem>(){
            override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    class UserViewHolder(binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root){
        val m_binding = binding

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item:UserListItem? = getItem(position)
        holder.m_binding.tvId.text = item?.id.toString()
        holder.m_binding.tvName.text = item?.login
        Glide
            .with(holder.m_binding.ivUser)
            .load(item?.avatarUrl)
            .centerCrop()
            .into(holder.m_binding.ivUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(ItemUserBinding.inflate(layoutInflater,parent,false))
    }

}