package ru.andlancer.examples.gamestream.core.network.api.params

data class GamesApiParams(
//    val page: Int? = null,
//    val pageSize: Int? = null,
    val dates: String? = null,
    val orfering: String? = null,
) {
    fun toMap(): Map<String, String> {
        return mutableMapOf<String, String>().apply {
//            page?.let { put("page", it.toString()) }
//            pageSize?.let { put("page_size", it.toString()) }
            dates?.let { put("dates", it) }
            orfering?.let { put("ordering", it) }
        }
    }
}
