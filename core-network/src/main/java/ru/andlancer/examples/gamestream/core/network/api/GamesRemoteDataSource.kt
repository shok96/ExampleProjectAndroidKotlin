package ru.andlancer.examples.gamestream.core.network.api

import ru.andlancer.examples.gamestream.core.network.api.params.GamesApiParams
import javax.inject.Inject
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import ru.andlancer.examples.gamestream.core.network.model.GameDTO

class GamesRemoteDataSource @Inject constructor(
    private val api: RawgApi
) {

    private val channel =
        ConflatedBroadcastChannel<PaggingState<List<GameDTO>>>(PaggingState.Initial)

    private var params: GamesApiParams? = null
    private var page = 1

    @Synchronized
    suspend fun initialLoading(params: GamesApiParams) {
        if (channel.value is PaggingState.Initial) {
            val data = api.games(
                params = params.applyPagingParams()
            )
           this. params = params
            channel.send(PaggingState.Content(data.result))
        }

    }

    @Synchronized
    suspend fun loadMore(index: Int) {
        var params = this.params ?: return
        val cachce = channel.value
        if (cachce is PaggingState.Content && index == cachce.data.size - 1) {
            channel.send(PaggingState.Pagging(cachce.data))
            val response = api.games(params = params.applyPagingParams(page = page + 1))
            channel.send(PaggingState.Content(cachce.data.plus(response.result)))
            page += 1
        }
    }

    fun observe(): Flow<PaggingState<List<GameDTO>>> = channel.asFlow()

    private companion object {
        const val DEFAULT_PAGE_SIzE = 20
    }

    private fun GamesApiParams.applyPagingParams(page: Int = 1): Map<String, String> =
        toMap().toMutableMap().apply {
            put("page", page.toString())
            put("page_size", DEFAULT_PAGE_SIzE.toString())
        }

}