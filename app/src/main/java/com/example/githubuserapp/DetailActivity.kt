package com.example.githubuserapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
        const val EXTRA_POSITION = "extra_position"
        const val EXTRA_PHOTO = "extra_photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvPhoto: ImageView = findViewById(R.id.tv_photo)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvUsername: TextView = findViewById(R.id.tv_username)
        val tvCompany: TextView = findViewById(R.id.tv_company)
        val tvLocation: TextView = findViewById(R.id.tv_location)
        val tvRepo: TextView = findViewById(R.id.tv_repo)
        val tvFollower: TextView = findViewById(R.id.tv_follower)
        val tvFollowing: TextView = findViewById(R.id.tv_following)

        val user = intent.getParcelableArrayListExtra<User>(EXTRA_USER) as ArrayList<User>
        val position = intent.getIntExtra(EXTRA_POSITION, 0)
        val photo = intent.getIntExtra(EXTRA_PHOTO, 0)

        tvPhoto.setImageResource(photo)
        tvName.text = user[position].name
        tvUsername.text = user[position].username
        tvCompany.text = user[position].company
        tvLocation.text = user[position].location
        tvRepo.text = user[position].repository.toString()
        tvFollower.text = user[position].follower.toString()
        tvFollowing.text = user[position].following.toString()
    }
}