package com.kevinhomorales.socialmediakotlin.users.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kevinhomorales.socialmediakotlin.R
import com.kevinhomorales.socialmediakotlin.databinding.RowUserBinding
import com.kevinhomorales.socialmediakotlin.networking.response.UsersResponse

class UserAdapter(private val context: Context, private var userClickListener: OnUserClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private lateinit var binding: RowUserBinding
    private var users = mutableListOf<UsersResponse>()

    fun setUsers(users: MutableList<UsersResponse>) {
        this.users = users
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        binding = RowUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val users = this.users[position]
        return holder.bind(users)
    }

    override fun getItemCount(): Int {
        return users.size.takeIf { it > 0 } ?: 0
    }

    inner class UserViewHolder(private val itemBinding: RowUserBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: UsersResponse) {
            itemBinding.nameId.text = user.name.uppercase()
            itemBinding.emailId.text = "Email: ${user.email}"
            itemBinding.usernameId.text = "Username: ${user.username}"
            val url = user.url
            Glide.with(context)
                .load(url)
                .placeholder(R.drawable.placeholderperson)
                .into(binding.userImageViewId)
            itemView.setOnClickListener { userClickListener.onUserClick(user) }
        }
    }
}