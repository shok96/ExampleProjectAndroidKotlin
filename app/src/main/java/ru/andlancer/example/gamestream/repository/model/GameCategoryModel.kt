package ru.andlancer.example.gamestream.repository.model

import ru.andlancer.examples.gamestream.core.network.api.PaggingState
import ru.andlancer.examples.gamestream.core.network.model.GameDTO

data class GameCategoryModel(
    val title: String,
    val category: CategoryType,
    val dataState: PaggingState<List<GameDTO>>
)
