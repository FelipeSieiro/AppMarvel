package com.felipesieiro.appmarvel.model

import java.io.Serializable

data class Heroes(
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val poster_path: String
) : Serializable