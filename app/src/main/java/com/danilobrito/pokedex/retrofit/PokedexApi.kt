package com.danilobrito.pokedex.retrofit

import com.danilobrito.pokedex.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PokedexApi {

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    fun provideApiService(retrofit: Retrofit): PokemonServices {
        return retrofit.create(PokemonServices::class.java)
    }
}