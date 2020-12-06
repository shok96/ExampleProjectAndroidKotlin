package ru.andlancer.example.gamestream

import ru.andlancer.example.gamestream.di.AppComponent
import ru.andlancer.examples.gamestream.core.network.di.NetworkComponent

object DI {
    lateinit var appComponent: AppComponent
    internal set

    lateinit var networkComponent: NetworkComponent
        internal set
}