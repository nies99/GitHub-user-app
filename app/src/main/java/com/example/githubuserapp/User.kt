package com.example.githubuserapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @field:SerializedName("username")
    var username: String?,

    @field:SerializedName("name")
    var name: String?,

    @field:SerializedName("avatar")
    var avatar: String?,

    @field:SerializedName("company")
    var company: String?,

    @field:SerializedName("location")
    var location: String?,

    @field:SerializedName("repository")
    var repository: Int?,

    @field:SerializedName("follower")
    var follower: Int?,

    @field:SerializedName("following")
    var following: Int?
) : Parcelable