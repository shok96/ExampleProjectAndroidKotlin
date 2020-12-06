package ru.andlancer.example.gamestream.model.game

import ru.andlancer.example.gamestream.model.base.ListItem

data class GameWideItem(
    val id: Long,
    val title: String,
    val image: String?
) : ListItem{
    override val itemId: Long = id
}
