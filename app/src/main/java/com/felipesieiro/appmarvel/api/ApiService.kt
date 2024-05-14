package com.felipesieiro.appmarvel.api

import com.felipesieiro.appmarvel.model.HeroesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.CollationKey

interface ApiService {
    @GET("heroe/popular")
    fun getPopularHeroes(@Query("api_key") apiKey: String): Call<HeroesResponse>

}