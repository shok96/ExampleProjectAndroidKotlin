package ru.andlancer.example.gamestream.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.andlancer.example.gamestream.model.base.ListItem
import ru.andlancer.example.gamestream.repository.model.CategoryType
import ru.andlancer.example.gamestream.ui.base.BaseDiffUtilItemCallback

class GameCardsAdapter(

    onReadyToLoadMore: (Int) -> Unit

) : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {
    init {
        setHasStableIds(true)
        delegatesManager.addDelegate(MainScreenDeligates.wideGameDelegate(onReadyToLoadMore))
            .addDelegate(MainScreenDeligates.thinGameDelegate(onReadyToLoadMore))
            .addDelegate(MainScreenDeligates.wideProgresDelegate())
            .addDelegate(MainScreenDeligates.thinProgresDelegate())

    }

    override fun getItemId(position: Int): Long {
        return items.get(position).itemId
    }
}