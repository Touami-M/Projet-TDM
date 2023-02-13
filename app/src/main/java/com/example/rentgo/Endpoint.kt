package com.example.rentgo

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface Endpoint {

    @FormUrlEncoded
    @POST("getuserhashmap")
    suspend fun getUserMap(@FieldMap data: HashMap<String,String>): Response<User?>

    @Multipart
    @POST("getuser")
    suspend fun getUser(@Part data: MultipartBody.Part): Response<User?>

    @GET("getcars")
    suspend fun getCars():Response<List<Car>>

    @Multipart
    @POST("createuser")
    suspend fun createUser(@Part image: MultipartBody.Part,
                           @Part user: MultipartBody.Part
    ):Response<String?>

    @FormUrlEncoded
    @POST("checkuser")
    suspend fun verifyUser(@FieldMap user: HashMap<String,String>):Response<User?>
}