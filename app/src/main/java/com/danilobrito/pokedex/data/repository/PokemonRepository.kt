package com.danilobrito.pokedex.data.repository

import com.danilobrito.pokedex.model.PokemonResponse
import com.danilobrito.pokedex.di.RetrofitInstance
import retrofit2.Response

class PokemonRepository {

    suspend fun getPokemon(): Response<PokemonResponse> {
        return RetrofitInstance.pokemonService.getPokemon()
    }

}
