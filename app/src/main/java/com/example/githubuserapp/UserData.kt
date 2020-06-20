package com.example.githubuserapp

import android.content.Context
import org.json.JSONArray
import java.io.IOException

object UserData {
    var userUsername = ArrayList<String>()
    var userName = ArrayList<String>()
    var userAvatar = ArrayList<String>()
    var userCompany = ArrayList<String>()
    var userLocation = ArrayList<String>()
    var userRepo = ArrayList<Int>()
    var userFollower = ArrayList<Int>()
    var userFollowing = ArrayList<Int>()

    val listData: ArrayList<User>
        get(){
            val list = arrayListOf<User>()
            for (position in 0..11){
                val user = User()
                user.username = userUsername[position]
                user.name = userName[position]
                user.avatar = userAvatar[position]
                user.company = userCompany[position]
                user.location = userLocation[position]
                user.repository = userRepo[position]
                user.follower = userFollower[position]
                user.following = userFollowing[position]
            }
            return list
        }
}