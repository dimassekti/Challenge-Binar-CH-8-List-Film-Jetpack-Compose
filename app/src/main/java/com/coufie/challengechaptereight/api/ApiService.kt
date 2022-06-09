@file:Suppress("unused", "unused", "unused")

package com.coufie.challengechaptereight.api

import com.coufie.challengechaptereight.data.GetFilmItem
import com.coufie.challengechaptereight.data.GetUserItem
import com.coufie.challengechaptereight.data.PostUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("film")
    suspend fun getAllNews(): List<GetFilmItem>

    @GET("user")
    suspend fun getAllUser(): List<GetUserItem>

    //Login
    @GET("user")
    fun getUser(
        @Query("username") username : String
    ) : Call<List<GetUserItem>>

    @POST("user")
    fun addUser(@Body reqUser: PostUser) : Call<GetUserItem>

//    @POST("user")
//    @FormUrlEncoded
//    fun postUser(
//        @Field("username") username: String,
//        @Field("password") password: String,
//        @Field("name") name: String,
//        @Field("address") address: String,
//        @Field("image") image: String,
//        @Field("umur") umur: String,
//    ): Call<GetUserItem>


}