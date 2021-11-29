package com.danilobrito.pokedex

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonServices {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonResponse
}