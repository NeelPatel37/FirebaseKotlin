package com.example.firebasekotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasekotlin.databinding.ItemUserDetailsBinding

class UserListAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {


    inner class UserListViewHolder(val binding: ItemUserDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: User) {
            binding.tvName.text = "${model.firstName} ${model.lastName}"
            binding.tvEmail.text = model.email
            binding.tvPhoneNumber.text = model.phoneNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ItemUserDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }
}