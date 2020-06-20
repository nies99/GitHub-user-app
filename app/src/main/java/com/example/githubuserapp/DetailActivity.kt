package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvPhoto: ImageView = findViewById(R.id.tv_photo)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvLocation: TextView = findViewById(R.id.tv_location)

        val user = intent.getParcelableExtra(EXTRA_USER) as User
        tvName.text = user.name
        tvLocation.text = user.location
        tvPhoto.setImageResource(user.avatar.toInt())
    }
}