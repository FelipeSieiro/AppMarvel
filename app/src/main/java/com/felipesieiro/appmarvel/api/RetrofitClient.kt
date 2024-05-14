package com.felipesieiro.appmarvel.api



object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}