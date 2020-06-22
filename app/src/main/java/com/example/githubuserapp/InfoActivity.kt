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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        svInfo = findViewById(R.id.scroll)

        fabMain = findViewById(R.id.fab_0)
        fab1Goto = findViewById(R.id.fab_1)
        fab2Info = findViewById(R.id.fab_2)
        fab3Up = findViewById(R.id.fab_3)

        fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.close)
        fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.open)
        fabLeft = AnimationUtils.loadAnimation(applicationContext, R.anim.left_rotate)
        fabRight = AnimationUtils.loadAnimation(applicationContext, R.anim.right_rotate)

        showAnimation()
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