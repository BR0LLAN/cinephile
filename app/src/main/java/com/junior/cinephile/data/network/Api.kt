package com.junior.cinephile.data.network

import com.junior.cinephile.entities.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("reviews/all.json?api-key=${HttpRoutes.API_KEY}")
    suspend fun getDataMovies(@Query("offset") offset: Int): Response
}