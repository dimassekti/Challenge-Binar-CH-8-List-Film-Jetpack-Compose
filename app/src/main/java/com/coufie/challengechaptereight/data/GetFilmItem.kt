package com.coufie.challengechaptereight.data


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetFilmItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
) : Serializable