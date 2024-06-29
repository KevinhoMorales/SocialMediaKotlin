package com.kevinhomorales.socialmediakotlin.post.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kevinhomorales.socialmediakotlin.R
import com.kevinhomorales.socialmediakotlin.databinding.ActivityPostBinding
import com.kevinhomorales.socialmediakotlin.main.MainActivity
import com.kevinhomorales.socialmediakotlin.networking.response.PostsResponse
import com.kevinhomorales.socialmediakotlin.post.viewmodel.PostViewModel
import com.kevinhomorales.socialmediakotlin.utils.Constants

class PostActivity : MainActivity() {
    lateinit var binding: ActivityPostBinding
    lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        if (intent.extras != null) {
            viewModel.post = intent.extras!!.get(Constants.POST_RESPONSE_KEY) as PostsResponse
        }
        binding.titleId.text = viewModel.post.title.uppercase()
        binding.bodyId.text = viewModel.post.body
        Glide.with(this)
            .load(viewModel.post.url)
            .placeholder(R.drawable.placeholderpost)
            .into(binding.postImageViewId)
        binding.sharePostButtonId.setOnClickListener {
            sharePost()
        }
    }

    private fun sharePost() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, viewModel.post.body);
        startActivity(Intent.createChooser(shareIntent,viewModel.post.title))
    }
}