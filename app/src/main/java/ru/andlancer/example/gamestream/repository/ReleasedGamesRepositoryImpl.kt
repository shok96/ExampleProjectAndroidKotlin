package ru.andlancer.example.gamestream.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.andlancer.example.gamestream.R
import ru.andlancer.example.gamestream.repository.model.CategoryType
import ru.andlancer.example.gamestream.repository.model.GameCategoryModel
import ru.andlancer.example.gamestream.util.ResourceProvider
import ru.andlancer.examples.gamestream.core.network.api.GamesRemoteDataSource
import ru.andlancer.examples.gamestream.core.network.api.params.GamesApiParams
import javax.inject.Inject

class ReleasedGamesRepositoryImpl @Inject constructor(
    private val dataSource: GamesRemoteDataSource,
    private val resourceProvider: ResourceProvider
) : GamesCategoryRepository {

    override fun data(): Flow<GameCategoryModel> {
        return dataSource.observe().map {
            GameCategoryModel(
                title = resourceProvider.string(R.string.latest_releases),
                dataState = it,
                category = CategoryType.Released
            )
        }
    }

    override suspend fun init() {
        dataSource.initialLoading(
            GamesApiParams(
                dates = "2020-05-16,2021-05-16",
            )
        )
    }

    override suspend fun loadMore(index: Int) {
        dataSource.loadMore(index)
    }

}