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

    @FormUrlEncoded
    @POST("getreserve")
    suspend fun getRents(@FieldMap data: HashMap<String, Int>):Response<List<Rent>>

    @FormUrlEncoded
    @POST("createreserve")
    suspend fun createReservation(@FieldMap data: HashMap<String,String>): Response<String?>

    @Multipart
    @POST("createuser")
    suspend fun createUser(@Part image: MultipartBody.Part,
                           @Part user: MultipartBody.Part
    ):Response<String?>

    @FormUrlEncoded
    @POST("checkuser")
    suspend fun verifyUser(@FieldMap user: HashMap<String,String>):Response<User?>

    @FormUrlEncoded
    @POST("rendreres")
    suspend fun rendreRes(@FieldMap idcar: java.util.HashMap<String, Int>):Response<String?>

    @FormUrlEncoded
    @POST("getsaved")
    suspend fun getSaved(@FieldMap data: HashMap<String, Int>):Response<List<Car>>

    @FormUrlEncoded
    @POST("createsaved")
    suspend fun createSaved(@FieldMap data: HashMap<String, Int>):Response<String?>

    @FormUrlEncoded
    @POST("removesaved")
    suspend fun deleteSaved(@FieldMap data: HashMap<String, Int>):Response<String?>
}