package com.kevinhomorales.socialmediakotlin.networking.response

data class UsersResponse (
    val id: Int,
    val name: String,
    val email: String,
    val username: String,
    var url: String
)