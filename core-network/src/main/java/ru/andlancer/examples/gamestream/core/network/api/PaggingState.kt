package ru.andlancer.examples.gamestream.core.network.api

sealed class PaggingState<out T> {

    object Initial: PaggingState<Nothing>()

    data class Content<T>(val data: T): PaggingState<T>()

    data class Pagging<T>(val availableContent: T): PaggingState<T>()


}