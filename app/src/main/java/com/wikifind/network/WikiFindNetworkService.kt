package com.wikifind.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wikifind.model.WikiResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL =
    "https://it.wikipedia.org/w/api.php/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface WikiFindNetworkService {
    @GET("?format=json&action=query&prop=extracts&explaintext=1&redirects=1&formatversion=2")
    suspend fun getWikiData(
        @Query("titles") title: String = "wikipedia"
    ): WikiResponse
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object WikiFindNetworkApi {
    val retrofitService: WikiFindNetworkService by lazy {
        retrofit.create(WikiFindNetworkService::class.java)
    }
}
