package com.danilobrito.pokedex.di.modules

import com.danilobrito.pokedex.data.repository.PokemonRepository
import com.danilobrito.pokedex.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<PokemonRepository> {
        PokemonRepository()
    }
    viewModel<PokemonViewModel> {
        PokemonViewModel(get())
    }
}