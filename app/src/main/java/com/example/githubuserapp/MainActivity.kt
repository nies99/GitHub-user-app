package com.example.githubuserapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        showRecyclerView()

        val obj = readJSON(this)
        list.addAll(UserData.listData)
    }

    private fun showRecyclerView() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val cardViewAdapter = Adapter(list)
        rvUsers.adapter = cardViewAdapter
    }

    fun readJSON(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("githubuser.json").bufferedReader().use { it.readText()}

            val jsonlist = JSONArray(jsonString)

            for (i in 0..jsonlist.length()){
                val jsonobj = jsonlist.getJSONObject(i)
                UserData.userUsername.add(jsonobj.getString("username"))
                UserData.userName.add(jsonobj.getString("name"))
                UserData.userAvatar.add(jsonobj.getString("avatar"))
                UserData.userCompany.add(jsonobj.getString("company"))
                UserData.userLocation.add(jsonobj.getString("location"))
                UserData.userRepo.add(jsonobj.getInt("repository"))
                UserData.userFollower.add(jsonobj.getInt("follower"))
                UserData.userFollowing.add(jsonobj.getInt("following"))
            }
        }
        catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}