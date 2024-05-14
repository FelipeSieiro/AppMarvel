package com.felipesieiro.appmarvel.model

import com.google.gson.annotations.SerializedName

data class HeroesResponse (

    @SerializedName("result") val movies: List<Heroes>
)