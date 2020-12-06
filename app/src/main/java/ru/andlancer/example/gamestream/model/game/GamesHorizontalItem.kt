package ru.andlancer.example.gamestream.model.game

import ru.andlancer.example.gamestream.model.base.ListItem
import ru.andlancer.example.gamestream.repository.model.CategoryType

data class GamesHorizontalItem(
    val title: String,
    val category: CategoryType,
    val games: List<ListItem>
): ListItem{
    override val itemId: Long = title.hashCode().toLong()
}