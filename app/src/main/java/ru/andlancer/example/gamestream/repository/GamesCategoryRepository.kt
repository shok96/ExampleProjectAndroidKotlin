package ru.andlancer.example.gamestream.repository

import kotlinx.coroutines.flow.Flow
import ru.andlancer.example.gamestream.repository.model.GameCategoryModel

interface GamesCategoryRepository {

    fun data(): Flow<GameCategoryModel>

    suspend fun init()

    suspend fun loadMore(index: Int)

}