package com.kevinhomorales.socialmediakotlin.main

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.kevinhomorales.socialmediakotlin.R
import com.kevinhomorales.socialmediakotlin.databinding.LoadingBinding
import com.kevinhomorales.socialmediakotlin.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class MainActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog
    private lateinit var binding: LoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun showLoading(text: String) {
        dialog = Dialog(this, R.style.Theme_AppCompat_Translucent)
        binding = LoadingBinding.inflate(layoutInflater)
        binding.textLoadingId.text = text
        binding
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.show()
    }

    fun hideLoading() {
        dialog.dismiss()
    }

    fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}