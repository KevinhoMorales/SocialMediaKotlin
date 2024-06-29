package com.kevinhomorales.socialmediakotlin.posts.view.adapter

import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse

interface OnPostClickListener {
    fun onPostClick(post: PostsResponse)
}