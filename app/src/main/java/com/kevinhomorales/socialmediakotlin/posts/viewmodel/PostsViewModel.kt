package com.kevinhomorales.socialmediakotlin.posts.viewmodel

import androidx.lifecycle.ViewModel
import com.kevinhomorales.socialmediakotlin.main.MainActivity
import com.kevinhomorales.socialmediakotlin.networking.model.PostsModel
import com.kevinhomorales.socialmediakotlin.networking.request.PostsRequest
import com.kevinhomorales.socialmediakotlin.networking.request.UsersRequest
import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse
import com.kevinhomorales.socialmediakotlin.networking.response.UsersResponse
import com.kevinhomorales.socialmediakotlin.utils.Alerts
import com.kevinhomorales.socialmediakotlin.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    lateinit var postsResponse: MutableList<PostsResponse>

    fun getPost(userId: Int) {

    }
}