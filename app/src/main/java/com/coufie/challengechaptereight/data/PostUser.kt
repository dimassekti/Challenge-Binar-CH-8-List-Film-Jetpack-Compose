package com.coufie.challengechaptereight.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostUser(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("umur")
    val umur: Int
): Serializable

