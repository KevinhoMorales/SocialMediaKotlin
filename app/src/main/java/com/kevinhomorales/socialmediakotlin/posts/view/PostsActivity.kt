package com.kevinhomorales.socialmediakotlin.posts.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kevinhomorales.socialmediakotlin.databinding.ActivityPostsBinding
import com.kevinhomorales.socialmediakotlin.main.MainActivity
import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse
import com.kevinhomorales.socialmediakotlin.post.view.PostActivity
import com.kevinhomorales.socialmediakotlin.posts.view.adapter.OnPostClickListener
import com.kevinhomorales.socialmediakotlin.posts.view.adapter.PostAdapter
import com.kevinhomorales.socialmediakotlin.posts.viewmodel.PostsViewModel
import com.kevinhomorales.socialmediakotlin.utils.Constants
import java.io.Serializable

class PostsActivity : MainActivity(), OnPostClickListener {
    private lateinit var binding: ActivityPostsBinding
    private lateinit var viewModel: PostsViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        if (intent.extras != null) {
            viewModel.postsResponse = intent.extras!!.get(Constants.POSTS_RESPONSE_KEY) as MutableList<PostsResponse>
        }
        postAdapter = PostAdapter(this, this)
        binding.postsRecyclerViewId.layoutManager = GridLayoutManager(this, 2)
        binding.postsRecyclerViewId.adapter = postAdapter
        getPosts()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getPosts() {
        postAdapter.setPosts(viewModel.postsResponse)
        postAdapter.notifyDataSetChanged()
    }

    override fun onPostClick(post: PostsResponse) {
        openPostView(post)
    }

    private fun openPostView(post: PostsResponse) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra(Constants.POST_RESPONSE_KEY, post as Serializable)
        startActivity(intent)
    }
}