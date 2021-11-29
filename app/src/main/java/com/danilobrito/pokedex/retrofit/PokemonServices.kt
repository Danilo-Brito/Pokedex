package com.danilobrito.pokedex.retrofit

import com.danilobrito.pokedex.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonServices {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonResponse
}