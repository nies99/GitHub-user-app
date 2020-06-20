package com.example.githubuserapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        getJSON(this)?.let { readJSON(it) }

        showRecyclerView()
    }

    private fun showRecyclerView() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val cardViewAdapter = Adapter(list)
        rvUsers.adapter = cardViewAdapter
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
}