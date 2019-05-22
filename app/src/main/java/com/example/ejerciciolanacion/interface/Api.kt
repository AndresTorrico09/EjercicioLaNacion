package com.example.ejerciciolanacion.`interface`

import retrofit2.Call
import com.example.ejerciciolanacion.model.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @get:GET("albums")
    val albums: Call<List<Album>>

    @GET("albums/{id}/photos")
    fun albumById(@Path("id") id: String): Call<Album>
}