package ru.andlancer.example.gamestream.util

import android.content.Context
import javax.inject.Inject

class AndroidResourceProvider @Inject constructor(
    private val context: Context
): ResourceProvider {
    override fun string(id: Int): String {
        return  context.resources.getString(id)
    }
}