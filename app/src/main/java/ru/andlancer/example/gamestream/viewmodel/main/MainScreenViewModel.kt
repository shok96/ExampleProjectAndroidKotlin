package ru.andlancer.example.gamestream.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.andlancer.example.gamestream.R
import ru.andlancer.example.gamestream.interactor.main.MainScreenInteractor
import ru.andlancer.example.gamestream.model.base.ListItem
import ru.andlancer.example.gamestream.model.game.*
import ru.andlancer.example.gamestream.repository.model.CategoryType
import ru.andlancer.example.gamestream.util.ResourceProvider
import ru.andlancer.example.gamestream.viewmodel.base.BaseViewModel
import ru.andlancer.examples.gamestream.core.network.api.GamesRemoteDataSource
import ru.andlancer.examples.gamestream.core.network.api.PaggingState
import ru.andlancer.examples.gamestream.core.network.api.RawgApi
import ru.andlancer.examples.gamestream.core.network.api.params.GamesApiParams
import ru.andlancer.examples.gamestream.core.network.di.NetworkComponent
import ru.andlancer.examples.gamestream.core.network.model.GameDTO
import java.text.FieldPosition
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val resources: ResourceProvider,
    private val interactor: MainScreenInteractor

) : BaseViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()

    val data: LiveData<List<ListItem>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO){
            interactor.data().collect {
                _data.postValue(it)
            }
        }
    }

    fun initCategory(item: GamesHorizontalItem){
        viewModelScope.launch(Dispatchers.IO){
            interactor.initCategory(item.category)
        }
    }

    fun readyToLoadMore(category: CategoryType, position: Int){
        viewModelScope.launch(Dispatchers.IO){
            interactor.tryToLoadMore(category, position)
        }
    }

}