package com.danilobrito.pokedex.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.data.repository.PokemonRepository
import com.danilobrito.pokedex.ui.adapter.PokemonAdapter
import com.danilobrito.pokedex.util.NetworkResult
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
        requestApiData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
    }

    private fun requestApiData() {
        viewModel.getPokemon()
        viewModel.pokemonResponse.observe(this, { response ->
            when (response){
                is NetworkResult.Success -> {
                    response.data?.let { adapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun init() {
        recyclerView = findViewById(R.id.recyclerView)
    }
}