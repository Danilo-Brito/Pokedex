package com.danilobrito.pokedex.repository

import com.danilobrito.pokedex.model.PokemonResponse
import com.danilobrito.pokedex.api.PokemonApi
import com.danilobrito.pokedex.retrofit.RetrofitInstance
import retrofit2.Response
import retrofit2.http.GET

class PokemonRepository {

    suspend fun getPokemon(): Response<PokemonResponse> {
        return RetrofitInstance.pokemonService.getPokemon()
    }

}
