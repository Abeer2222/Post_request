package com.example.postrequest

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("/test/")
    fun doGetListResources(): Call<List<UserDetails.User>>

    @POST("/test/")
    fun addUser(
        @Body userData: UserDetails.User
    ): Call<UserDetails.User>

    @PUT("/test/{id}")
    fun updateUser(@Path("id") id:Int, @Body userData: UserDetails.User): Call<UserDetails.User>

    @Headers("Content-")
    @DELETE("/test/{id}")
    fun deletUser(@Path("id") id: Int): Call<Void>//void for overwrite
}