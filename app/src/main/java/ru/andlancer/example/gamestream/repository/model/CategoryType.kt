package ru.andlancer.example.gamestream.repository.model

sealed class CategoryType{
    object MostRelated: CategoryType()
    object Released: CategoryType()
    object Rated: CategoryType()

    data class Genre(val id: Long): CategoryType()
}
