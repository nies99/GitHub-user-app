package com.example.githubuserapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        showAnimation()
    }

    private fun showAnimation() {
        var isOpen = false

        val svInfo: ScrollView = findViewById(R.id.scroll)

        val fabMain: FloatingActionButton = findViewById(R.id.fab_0)
        val fab1Goto: ExtendedFloatingActionButton = findViewById(R.id.fab_1)
        val fab2Back: ExtendedFloatingActionButton = findViewById(R.id.fab_2)
        val fab3Up: ExtendedFloatingActionButton = findViewById(R.id.fab_3)

        val fabClose: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.close)
        val fabOpen: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.open)
        val fabLeft: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.left_rotate)
        val fabRight: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.right_rotate)

        fabMain.setOnClickListener {
            if (isOpen) {
                fab1Goto.startAnimation(fabClose)
                fab2Back.startAnimation(fabClose)
                fab3Up.startAnimation(fabClose)
                fabMain.startAnimation(fabLeft)
                fab1Goto.isClickable = false
                fab2Back.isClickable = false
                fab3Up.isClickable = false
                isOpen = false
            } else {
                fab1Goto.startAnimation(fabOpen)
                fab2Back.startAnimation(fabOpen)
                fab3Up.startAnimation(fabOpen)
                fabMain.startAnimation(fabRight)
                fab1Goto.isClickable = true
                fab2Back.isClickable = true
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

        fab2Back.setOnClickListener {
            finish()
        }

        fab3Up.setOnClickListener {
            svInfo.post {
                svInfo.smoothScrollTo(0, 0)
            }
        }
    }
}