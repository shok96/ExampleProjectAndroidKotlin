package ru.andlancer.example.gamestream.interactor.main

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.andlancer.example.gamestream.model.base.ListItem
import ru.andlancer.example.gamestream.model.game.*
import ru.andlancer.example.gamestream.repository.GamesCategoryRepository
import ru.andlancer.example.gamestream.repository.MostRelatedGamesRepositoryImpl
import ru.andlancer.example.gamestream.repository.RatedGamesRepositoryImpl
import ru.andlancer.example.gamestream.repository.ReleasedGamesRepositoryImpl
import ru.andlancer.example.gamestream.repository.model.CategoryType
import ru.andlancer.example.gamestream.repository.model.GameCategoryModel
import ru.andlancer.example.gamestream.util.ResourceProvider
import ru.andlancer.examples.gamestream.core.network.api.GamesRemoteDataSource
import ru.andlancer.examples.gamestream.core.network.api.PaggingState
import ru.andlancer.examples.gamestream.core.network.api.RawgApi
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainScreenInteractorImpl @Inject constructor(
    api: RawgApi,
    resourceProvider: ResourceProvider
) : MainScreenInteractor {

    private val mostRelatedGamesRepository: GamesCategoryRepository =
        MostRelatedGamesRepositoryImpl(
            GamesRemoteDataSource(api), resourceProvider
        )
    private val releasedRemoteDataSource: GamesCategoryRepository = ReleasedGamesRepositoryImpl(
        GamesRemoteDataSource(api), resourceProvider
    )
    private val ratedRemoteDataSource: GamesCategoryRepository = RatedGamesRepositoryImpl(
        GamesRemoteDataSource(api), resourceProvider
    )

    override fun data(): Flow<List<ListItem>> {
        return combine(
            mostRelatedGamesRepository.data(),
            releasedRemoteDataSource.data(),
            ratedRemoteDataSource.data()
        ) { most, released, rated ->
            listOf(
                mapToCategory(most, true),
                mapToCategory(released),
                mapToCategory(rated, true)
            )
        }
    }

    override suspend fun initCategory(category: CategoryType) {
        when(category){
            is CategoryType.MostRelated -> mostRelatedGamesRepository.init()
            is CategoryType.Released -> releasedRemoteDataSource.init()
            is CategoryType.Rated -> ratedRemoteDataSource.init()
        }
    }

    override suspend fun tryToLoadMore(category: CategoryType, index: Int) {
        when(category){
            is CategoryType.MostRelated -> mostRelatedGamesRepository.loadMore(index)
            is CategoryType.Released -> releasedRemoteDataSource.loadMore(index)
            is CategoryType.Rated -> ratedRemoteDataSource.loadMore(index)
        }
    }

    private fun mapToCategory(model: GameCategoryModel, wide: Boolean = false): ListItem {
        return when (model.dataState) {
            is PaggingState.Initial -> {
                GamesHorizontalItem(
                    title = model.title,
                    games = IntRange(1, 2).map { if(wide) ProgressWideItem else ProgressThinItem },
                    category = model.category
                )
            }
            is PaggingState.Content -> {
                GamesHorizontalItem(
                    title = model.title,
                    category = model.category,
                    games = model.dataState.data.map {
                        if (wide) {
                            GameWideItem(
                                id = it.id,
                                title = it.title,
                                image = it.image
                            )
                        }
                        else{
                            GameThinItem(
                                id = it.id,
                                title = it.title,
                                image = it.image
                            )
                        }
                    }
                )
            }
            is PaggingState.Pagging -> {
                GamesHorizontalItem(
                    title = model.title,
                    category = model.category,
                    games = model.dataState.availableContent.map {
                        if (wide) {
                            GameWideItem(
                                id = it.id,
                                title = it.title,
                                image = it.image
                            )
                        }
                        else{
                            GameThinItem(
                                id = it.id,
                                title = it.title,
                                image = it.image
                            )
                        }
                    }
                        .plus(if(wide) ProgressWideItem else ProgressThinItem)
                )
            }
            else -> throw  IllegalArgumentException("Wrong pagginf state ${model.dataState}")
        }
    }

}