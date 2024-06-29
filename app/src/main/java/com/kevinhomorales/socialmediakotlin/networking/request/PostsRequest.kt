package com.kevinhomorales.socialmediakotlin.networking.request

import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface PostsRequest {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET()
    suspend fun getPosts(@Url endpoint: String): Response<MutableList<PostsResponse>>
}