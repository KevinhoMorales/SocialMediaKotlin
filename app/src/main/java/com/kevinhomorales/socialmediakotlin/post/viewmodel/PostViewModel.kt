package com.kevinhomorales.socialmediakotlin.post.viewmodel

import androidx.lifecycle.ViewModel
import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse

class PostViewModel: ViewModel() {
    lateinit var post: PostsResponse
}