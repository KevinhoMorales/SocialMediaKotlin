package com.kevinhomorales.socialmediakotlin.users.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinhomorales.socialmediakotlin.databinding.ActivityUsersBinding
import com.kevinhomorales.socialmediakotlin.main.MainActivity
import com.kevinhomorales.socialmediakotlin.networking.model.PostsModel
import com.kevinhomorales.socialmediakotlin.networking.model.UsersModel
import com.kevinhomorales.socialmediakotlin.networking.response.PhotosResponse
import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse
import com.kevinhomorales.socialmediakotlin.networking.response.UsersResponse
import com.kevinhomorales.socialmediakotlin.posts.view.PostsActivity
import com.kevinhomorales.socialmediakotlin.users.view.adapter.OnUserClickListener
import com.kevinhomorales.socialmediakotlin.users.view.adapter.UserAdapter
import com.kevinhomorales.socialmediakotlin.users.viewmodel.UsersViewModel
import com.kevinhomorales.socialmediakotlin.utils.Constants
import java.io.Serializable

class UsersActivity : MainActivity(), OnUserClickListener {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var viewModel: UsersViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        userAdapter = UserAdapter(this, this)
        binding.usersRecyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerViewId.adapter = userAdapter
        getUsers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getUsers() {
        viewModel.getUsers(this) { usersResponse, photosResponse ->
            val randomTenPhotos = photosResponse.shuffled().take(10) as MutableList<PhotosResponse>
            usersResponse.forEach { user ->
                user.url = randomTenPhotos.random().url
            }
            userAdapter.setUsers(usersResponse)
            userAdapter.notifyDataSetChanged()
        }
    }
    override fun onUserClick(user: UsersResponse) {
        val postsModel = PostsModel(user.id)
        viewModel.getPosts(this, postsModel) { postsResponses ->
            val randomTenPhotos = viewModel.photos.shuffled().take(10) as MutableList<PhotosResponse>
            postsResponses.forEach { post ->
                post.url = randomTenPhotos.random().url
            }
            val postsByUser = postsResponses.filter { it.userId == user.id } as MutableList<PostsResponse>
            openPostsView(postsByUser)
        }
    }

    private fun openPostsView(postsResponse: MutableList<PostsResponse>) {
        val intent = Intent(this, PostsActivity::class.java)
        intent.putExtra(Constants.POSTS_RESPONSE_KEY, postsResponse as Serializable)
        startActivity(intent)
    }
}