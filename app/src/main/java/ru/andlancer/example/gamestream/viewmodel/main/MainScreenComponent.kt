package ru.andlancer.example.gamestream.viewmodel.main

import android.content.res.loader.ResourcesProvider
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import ru.andlancer.example.gamestream.DI
import ru.andlancer.example.gamestream.di.ScreenScope
import ru.andlancer.example.gamestream.di.ViewModelKey
import ru.andlancer.example.gamestream.di.ViewModelfactory
import ru.andlancer.example.gamestream.interactor.main.MainScreenInteractor
import ru.andlancer.example.gamestream.interactor.main.MainScreenInteractorImpl
import ru.andlancer.example.gamestream.util.ResourceProvider
import ru.andlancer.examples.gamestream.core.network.api.RawgApi

@Component(modules = [MainScreenModule::class])
@ScreenScope
interface MainScreenComponent {

    fun viewModelfactory(): ViewModelfactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun resources(resourcesProvider: ResourceProvider): Builder

        @BindsInstance
        fun api(api:RawgApi): Builder

        fun build(): MainScreenComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerMainScreenComponent.builder()
                .resources(resources())
                .api(DI.networkComponent.api())
                .build()
        }
    }
}

@Module
abstract class MainScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun mainScreenViewModel(viewModel: MainScreenViewModel): ViewModel


    @Binds
    @ScreenScope
    abstract fun mainScreenInteractor(interactor: MainScreenInteractorImpl): MainScreenInteractor



}