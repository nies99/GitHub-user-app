package com.example.githubuserapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

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
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        fabMain = findViewById(R.id.fab_0)
        fab1Goto = findViewById(R.id.fab_1)
        fab2Info = findViewById(R.id.fab_2)
        fab3Up = findViewById(R.id.fab_3)

        fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.close)
        fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.open)
        fabLeft = AnimationUtils.loadAnimation(applicationContext, R.anim.left_rotate)
        fabRight = AnimationUtils.loadAnimation(applicationContext, R.anim.right_rotate)

        showAnimation()

        getJSON(this)?.let { readJSON(it) }

        showRecyclerView()
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
            fab1Goto.startAnimation(fabClose)
            fab2Info.startAnimation(fabClose)
            fab3Up.startAnimation(fabClose)
            fabMain.startAnimation(fabLeft)
            fab1Goto.isClickable = false
            fab2Info.isClickable = false
            fab3Up.isClickable = false
            isOpen = false

            val info = Intent(this@MainActivity, InfoActivity::class.java)
            startActivity(info)
        }

        fab3Up.setOnClickListener {
            fab1Goto.startAnimation(fabClose)
            fab2Info.startAnimation(fabClose)
            fab3Up.startAnimation(fabClose)
            fabMain.startAnimation(fabLeft)
            fab1Goto.isClickable = false
            fab2Info.isClickable = false
            fab3Up.isClickable = false
            isOpen = false

            rvUsers.post {
                rvUsers.smoothScrollToPosition(0)
            }
        }
    }

    private fun getJSON(context: Context): String? {
        val jsonString: String

        try {
            jsonString = context.assets.open("githubuser.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun readJSON(response: String) {
        val objectJSON = JSONObject(response)
        val userArray = objectJSON.getJSONArray("users")

        for (obj in 0 until userArray.length()) {
            val userObject = userArray.getJSONObject(obj)
            val username = userObject.getString("username")
            val name = userObject.getString("name")
            val avatar = userObject.getString("avatar")
            val company = userObject.getString("company")
            val location = userObject.getString("location")
            val repo = userObject.getInt("repository")
            val follower = userObject.getInt("follower")
            val following = userObject.getInt("following")

            val user = User(
                username = username,
                name = name,
                avatar = avatar,
                company = company,
                location = location,
                repository = repo,
                follower = follower,
                following = following
            )
            list.add(user)
        }
    }

    private fun showRecyclerView() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val cardViewAdapter = Adapter(list)
        rvUsers.adapter = cardViewAdapter
    }
}