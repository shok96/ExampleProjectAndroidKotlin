package ru.andlancer.example.gamestream

import android.app.Application
import ru.andlancer.example.gamestream.di.DaggerAppComponent
import ru.andlancer.examples.gamestream.core.network.di.DaggerNetworkComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI(){
        DI.appComponent = DaggerAppComponent.builder()
            .appContext(this)
            .build()

        DI.networkComponent = DaggerNetworkComponent.create()

    }

}