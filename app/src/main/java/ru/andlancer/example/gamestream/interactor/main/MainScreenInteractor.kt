package ru.andlancer.example.gamestream.interactor.main

import kotlinx.coroutines.flow.Flow
import ru.andlancer.example.gamestream.model.base.ListItem
import ru.andlancer.example.gamestream.repository.model.CategoryType

interface MainScreenInteractor {

    fun data(): Flow<List<ListItem>>

    suspend fun initCategory(category: CategoryType)

    suspend fun tryToLoadMore(category: CategoryType, index: Int)

}