package com.danilobrito.pokedex.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.data.repository.PokemonRepository
import com.danilobrito.pokedex.ui.adapter.PokemonAdapter
import com.danilobrito.pokedex.viewmodel.PokemonViewModel
import com.danilobrito.pokedex.viewmodel.PokemonViewModelFactory

class PokedexActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    private val adapter by lazy {
        PokemonAdapter(context = this)
    }

    private val viewModel by lazy {
        val repository = PokemonRepository()
        val factory = PokemonViewModelFactory(repository)
        val provide = ViewModelProvider(this, factory)
        provide.get(PokemonViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_pokedex)
        init()
        findPokemon()
        configRecyclerView()
    }

    private fun configRecyclerView() {
        recyclerView.adapter = adapter
    }

    private fun findPokemon() {
        viewModel.getPokemon()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                adapter.pokemon = response.body()?.results!!
            }
        })
    }

    private fun init() {
        recyclerView = findViewById(R.id.recyclerView)
    }
}