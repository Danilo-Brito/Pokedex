package com.danilobrito.pokedex.repository

import com.danilobrito.pokedex.model.PokemonResponse
import com.danilobrito.pokedex.retrofit.PokemonApi

class PokedexRepository(
    private val pokemonApi: PokemonApi
) {
    fun getPokemon() {
        val response = pokemonApi.getPokemon()
    }

}
