package ru.andlancer.example.gamestream.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.andlancer.example.gamestream.DI
import ru.andlancer.example.gamestream.R
import ru.andlancer.example.gamestream.databinding.FragmentMainBinding
import ru.andlancer.example.gamestream.ui.base.viewBinding
import ru.andlancer.example.gamestream.viewmodel.main.DaggerMainScreenComponent
import ru.andlancer.example.gamestream.viewmodel.main.MainScreenComponent
import ru.andlancer.example.gamestream.viewmodel.main.MainScreenViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val component by lazy {
        MainScreenComponent.create()
    }

    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by viewModels<MainScreenViewModel>() { component.viewModelfactory() }

    private val adapter by lazy {
        MainScreenAdapter(
            onItemBind = viewModel::initCategory,
                    onReadyToLoadMore = viewModel::readyToLoadMore
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding) {
            rec.adapter = adapter

            viewModel.data.observe(viewLifecycleOwner, Observer {
                adapter.apply {
                    items = it

                }
            })

        }
    }


}