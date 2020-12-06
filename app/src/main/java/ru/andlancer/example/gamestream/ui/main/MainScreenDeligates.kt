package ru.andlancer.example.gamestream.ui.main

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.andlancer.example.gamestream.R
import ru.andlancer.example.gamestream.databinding.*
import ru.andlancer.example.gamestream.model.base.ListItem
import ru.andlancer.example.gamestream.model.game.*
import ru.andlancer.example.gamestream.repository.model.CategoryType


object MainScreenDeligates {

    fun gamesHorizontalDeligate(
        onItemBind: (GamesHorizontalItem) -> Unit,
        onReadyToLoadMore: (CategoryType, Int) -> Unit,
    ) =
        adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
            { inflater, container ->
                ItemGamesHorizontalBinding.inflate(inflater, container, false)
                    .apply {

                    }
            }
        ) {
            //onCreateViewHolder
            val adapter = GameCardsAdapter{ pos -> onReadyToLoadMore.invoke(item.category, pos) }
            binding.rec.adapter = adapter
            bind {
                //onBindViewHolder
                onItemBind.invoke(item)
                adapter.apply {
                    binding.titleTextView.text = item.title
                    items = item.games
                }
            }


        }

    fun wideGameDelegate(onReadyToLoadMore: (Int) -> Unit) =
        adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
            { inflater, container -> ItemGameWideBinding.inflate(inflater, container, false) }
        )
        {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .override(
                        resources.getDimensionPixelOffset(R.dimen.game_card_wide_width),
                        resources.getDimensionPixelOffset(R.dimen.game_card_wide_height),
                    )
                    .transform(
                        CenterCrop(),
                        RoundedCorners(resources.getDimensionPixelOffset(R.dimen.game_card_radius))
                    )
                    .transition(withCrossFade())
                    .into(binding.img)
                binding.title = item.title
                binding.executePendingBindings()

                onReadyToLoadMore.invoke(adapterPosition)
            }
            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
                    Glide.with(binding.root).clear(binding.img)
            }
        }

    fun thinGameDelegate(onReadyToLoadMore: (Int) -> Unit) =
        adapterDelegateViewBinding<GameThinItem, ListItem, ItemGameThinBinding>(
            { inflater, container -> ItemGameThinBinding.inflate(inflater, container, false) }
        )
        {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .override(
                        resources.getDimensionPixelOffset(R.dimen.game_card_thin_width),
                        resources.getDimensionPixelOffset(R.dimen.game_card_thin_height),
                    )
                    .transform(
                        CenterCrop(),
                        RoundedCorners(resources.getDimensionPixelOffset(R.dimen.game_card_radius))
                    )
                    .transition(withCrossFade())
                    .into(binding.img)
                binding.title = item.title
                binding.executePendingBindings()
                onReadyToLoadMore.invoke(adapterPosition)
            }

            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
                    Glide.with(binding.root).clear(binding.img)
            }
        }

    fun wideProgresDelegate() =
        adapterDelegateViewBinding<ProgressWideItem, ListItem, ItemProgressWideBinding>(
            { inflater, container -> ItemProgressWideBinding.inflate(inflater, container, false) }
        )
        {}

    fun thinProgresDelegate() =
        adapterDelegateViewBinding<ProgressThinItem, ListItem, ItemProgressThinBinding>(
            { inflater, container -> ItemProgressThinBinding.inflate(inflater, container, false) }
        )
        {}

}