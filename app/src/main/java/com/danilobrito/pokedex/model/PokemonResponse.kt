package com.danilobrito.pokedex.model

import com.danilobrito.pokedex.model.Pokemon

class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)