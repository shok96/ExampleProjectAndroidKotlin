package ru.andlancer.examples.gamestream.core.network.model.base

import com.google.gson.annotations.SerializedName
import ru.andlancer.examples.gamestream.core.network.model.GameDTO

data class PagedResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val nexPageUrl: String,
    @SerializedName("previous") val previousPageUrl: String,
    @SerializedName("results") val result: List<GameDTO>,
)