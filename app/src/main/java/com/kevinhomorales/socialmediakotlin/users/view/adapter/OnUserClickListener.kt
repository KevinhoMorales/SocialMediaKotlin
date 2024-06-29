package com.kevinhomorales.socialmediakotlin.users.view.adapter

import com.kevinhomorales.socialmediakotlin.networking.response.UsersResponse

interface OnUserClickListener {
    fun onUserClick(user: UsersResponse)
}