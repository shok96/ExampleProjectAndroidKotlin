package ru.andlancer.examples.gamestream.core.network.di

import android.os.Build
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.andlancer.examples.gamestream.core.network.BuildConfig
import ru.andlancer.examples.gamestream.core.network.api.RawgApi


import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {

    fun api():RawgApi


}

@Module
abstract class NetworkModule{

    companion object{

        private const val BASE_URL = "https://api.rawg.io/"

        @Provides
        @Singleton
        fun provideApi(): RawgApi{
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level =
                                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                        })
                        .build()
                )
                .build()
                .create(RawgApi::class.java)

        }
    }

}