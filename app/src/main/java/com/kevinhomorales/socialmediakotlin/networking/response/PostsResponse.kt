package com.kevinhomorales.socialmediakotlin.networking.response

import java.io.Serializable

data class PostsResponse (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var url: String
): Serializable