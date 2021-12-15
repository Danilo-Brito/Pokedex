package com.danilobrito.pokedex.model

class PokemonDetail(
    val abilities: List<Abilities>,
    val base_experience: String,
    val forms: List<Forms>,
    val height: String,
    val held_items: Items,
    val moves: List<Moves>,
    val name: String,
    val species: List<Species>,
    val sprites: List<Sprites>,
    val stats: List<Stats>,
    val types: List<Types>,
    val weight: String
)
