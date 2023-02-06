package com.wikifind.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WikiResponse(
    @Json(name = "batchcomplete") val batchcomplete: Boolean,
    @Json(name = "query") val query: QueryField
)

data class QueryField(
    val normalized: Array<Any>? = null, val pages: Array<Page>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QueryField

        if (!normalized.contentEquals(other.normalized)) return false
        if (!pages.contentEquals(other.pages)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = normalized.contentHashCode()
        result = 31 * result + pages.contentHashCode()
        return result
    }
}


data class Page(val title: String, val extract: String? = null, val missing: Boolean?)