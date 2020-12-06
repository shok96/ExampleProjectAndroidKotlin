package ru.andlancer.example.gamestream.di

import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.andlancer.example.gamestream.util.AndroidResourceProvider
import ru.andlancer.example.gamestream.util.ResourceProvider
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun resources(): ResourceProvider

  @Component.Builder
  interface Builder{
      @BindsInstance
      fun appContext(context: Context): Builder

      fun build():AppComponent
  }

}

@Module
abstract class AppModule{

@Binds
@Singleton
abstract fun bindResouceProvider(provider: AndroidResourceProvider): ResourceProvider

}