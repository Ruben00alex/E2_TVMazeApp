package com.example.examen2_android.data.api


import com.example.examen2_android.model.Show
import com.example.examen2_android.model.ShowSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("shows")
    suspend fun getShows(): List<Show>

    @GET("search/shows")
    suspend fun searchShows(@Query("q") query: String): List<ShowSearchResponse>

    @GET("shows/{id}")
    suspend fun getShowDetails(@Path("id") id: Int, @Query("embed") embed: String = "cast"): Show
}
