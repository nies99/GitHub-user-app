package com.example.githubuserapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    private lateinit var svInfo: ScrollView

    private lateinit var fabMain: FloatingActionButton
    private lateinit var fab1Goto: ExtendedFloatingActionButton
    private lateinit var fab2Info: ExtendedFloatingActionButton
    private lateinit var fab3Up: ExtendedFloatingActionButton
    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var fabRight: Animation
    private lateinit var fabLeft: Animation

    private var isOpen: Boolean = false

    companion object{
        const val EXTRA_USER = "extra_user"
        const val EXTRA_POSITION = "extra_position"
        const val EXTRA_PHOTO = "extra_photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        svInfo = findViewById(R.id.scroll_detail)

        fabMain = findViewById(R.id.fab_0)
        fab1Goto = findViewById(R.id.fab_1)
        fab2Info = findViewById(R.id.fab_2)
        fab3Up = findViewById(R.id.fab_3)

        fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.close)
        fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.open)
        fabLeft = AnimationUtils.loadAnimation(applicationContext, R.anim.left_rotate)
        fabRight = AnimationUtils.loadAnimation(applicationContext, R.anim.right_rotate)

        showAnimation()

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

    private fun showAnimation() {
        fabMain.setOnClickListener {
            if (isOpen) {
                fab1Goto.startAnimation(fabClose)
                fab2Info.startAnimation(fabClose)
                fab3Up.startAnimation(fabClose)
                fabMain.startAnimation(fabLeft)
                fab1Goto.isClickable = false
                fab2Info.isClickable = false
                fab3Up.isClickable = false
                isOpen = false
            } else {
                fab1Goto.startAnimation(fabOpen)
                fab2Info.startAnimation(fabOpen)
                fab3Up.startAnimation(fabOpen)
                fabMain.startAnimation(fabRight)
                fab1Goto.isClickable = true
                fab2Info.isClickable = true
                fab3Up.isClickable = true
                isOpen = true
            }
        }

        fab1Goto.setOnClickListener {
            fab1Goto.setIconTintResource(R.color.colorAccent)
            fab1Goto.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))

            val goTo = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/"))
            startActivity(goTo)
        }

        fab2Info.setOnClickListener {
            finish()
        }

        fab3Up.setOnClickListener {
            svInfo.post {
                svInfo.smoothScrollTo(0, 0)
            }
        }
    }
}