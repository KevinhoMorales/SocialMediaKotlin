package com.kevinhomorales.socialmediakotlin.posts.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kevinhomorales.socialmediakotlin.R
import com.kevinhomorales.socialmediakotlin.databinding.RowPostBinding
import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse

class PostAdapter(private val context: Context, private var postClickListener: OnPostClickListener): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private lateinit var binding: RowPostBinding
    private var posts = mutableListOf<PostsResponse>()

    fun setPosts(posts: MutableList<PostsResponse>) {
        this.posts = posts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        binding = RowPostBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        val posts = this.posts[position]
        return holder.bind(posts)
    }

    override fun getItemCount(): Int {
        return posts.size.takeIf { it > 0 } ?: 0
    }

    inner class PostViewHolder(private val itemBinding: RowPostBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(post: PostsResponse) {
            itemBinding.titleId.text = post.title.uppercase()
            val url = post.url
            Glide.with(context)
                .load(url)
                .placeholder(R.drawable.placeholderpost)
                .into(binding.postImageViewId)
            itemView.setOnClickListener { postClickListener.onPostClick(post) }
        }
    }
}