package com.kevinhomorales.socialmediakotlin.networking.request

import com.kevinhomorales.socialmediakotlin.networking.response.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface UsersRequest {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET()
    suspend fun getUsers(@Url endpoint: String): Response<MutableList<UsersResponse>>
}