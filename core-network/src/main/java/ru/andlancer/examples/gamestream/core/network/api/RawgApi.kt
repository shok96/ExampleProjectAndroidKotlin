package ru.andlancer.examples.gamestream.core.network.api

import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.andlancer.examples.gamestream.core.network.model.base.PagedResponse

interface RawgApi {

    @GET("/api/games")
    suspend fun games(@QueryMap params: Map<String, String>): PagedResponse

}