package ru.andlancer.examples.gamestream.core.network.model

import com.google.gson.annotations.SerializedName

data class GameDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val title: String,
    @SerializedName("background_image") val image: String?

)
