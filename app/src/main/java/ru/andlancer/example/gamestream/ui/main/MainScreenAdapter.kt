package ru.andlancer.example.gamestream.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.andlancer.example.gamestream.model.base.ListItem
import ru.andlancer.example.gamestream.model.game.GamesHorizontalItem
import ru.andlancer.example.gamestream.repository.model.CategoryType
import ru.andlancer.example.gamestream.ui.base.BaseDiffUtilItemCallback

class MainScreenAdapter(
    onItemBind: (GamesHorizontalItem) -> Unit,
    onReadyToLoadMore: (CategoryType, Int) -> Unit,
): AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(MainScreenDeligates.gamesHorizontalDeligate(
            onItemBind = onItemBind,
            onReadyToLoadMore = onReadyToLoadMore
        ))
    }

}