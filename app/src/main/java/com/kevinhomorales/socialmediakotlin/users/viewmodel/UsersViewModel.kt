package com.kevinhomorales.socialmediakotlin.users.viewmodel

import androidx.lifecycle.ViewModel
import com.kevinhomorales.socialmediakotlin.main.MainActivity
import com.kevinhomorales.socialmediakotlin.networking.model.PostsModel
import com.kevinhomorales.socialmediakotlin.networking.model.UsersModel
import com.kevinhomorales.socialmediakotlin.networking.request.PhotosRequest
import com.kevinhomorales.socialmediakotlin.networking.request.PostsRequest
import com.kevinhomorales.socialmediakotlin.networking.request.UsersRequest
import com.kevinhomorales.socialmediakotlin.networking.response.PhotosResponse
import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse
import com.kevinhomorales.socialmediakotlin.networking.response.UsersResponse
import com.kevinhomorales.socialmediakotlin.users.view.adapter.UserAdapter
import com.kevinhomorales.socialmediakotlin.utils.Alerts
import com.kevinhomorales.socialmediakotlin.utils.Constants
import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {
    lateinit var photos: MutableList<PhotosResponse>

    fun getUsers(context: MainActivity, completion: (MutableList<UsersResponse>, MutableList<PhotosResponse>) -> Unit) {
        context.showLoading(Constants.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
            val call = context.getRetrofit().create(UsersRequest::class.java).getUsers("users")
            val usersResponse = call.body()
            context.runOnUiThread {
                context.hideLoading()
                if ((call.isSuccessful) && usersResponse != null) {
                    if (usersResponse.isEmpty()) {
                        Alerts.warning("Error", "No hay datos", context)
                        return@runOnUiThread
                    }
                    getPhotos(context, usersResponse, completion)
                }
            }
        }
    }

    private fun getPhotos(context: MainActivity, usersResponse: MutableList<UsersResponse>, completion: (MutableList<UsersResponse>, MutableList<PhotosResponse>) -> Unit) {
        context.showLoading(Constants.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
            val call = context.getRetrofit().create(PhotosRequest::class.java).getPhotos("photos")
            val photosResponse = call.body()
            context.runOnUiThread {
                context.hideLoading()
                if ((call.isSuccessful) && photosResponse != null) {
                    photos = photosResponse
                    if (photosResponse.isEmpty()) {
                        Alerts.warning("Error", "No hay datos", context)
                        return@runOnUiThread
                    }
                    completion(usersResponse, photosResponse)
                }
            }
        }
    }

    fun getPosts(context: MainActivity, postsModel: PostsModel, completion: (MutableList<PostsResponse>) -> Unit) {
        context.showLoading(Constants.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
            val call = context.getRetrofit().create(PostsRequest::class.java).getPosts("posts")
            val postsResponse = call.body()
            context.runOnUiThread {
                context.hideLoading()
                if ((call.isSuccessful) && postsResponse != null) {
                    if (postsResponse.isEmpty()) {
                        Alerts.warning("Error", "No hay datos", context)
                        return@runOnUiThread
                    }
                    completion(postsResponse)
                }
            }
        }
    }
}
