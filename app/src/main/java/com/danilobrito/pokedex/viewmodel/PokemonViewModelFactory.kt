package com.danilobrito.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danilobrito.pokedex.repository.PokemonRepository

class PokemonViewModelFactory(private val repository: PokemonRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonViewModel(repository) as T
    }
}