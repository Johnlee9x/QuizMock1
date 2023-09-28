package com.example.quizmock1.data.api

import com.example.quizmock1.data.model.firebase.User
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("/users")
    suspend fun getUser(): Response<List<User>>
}