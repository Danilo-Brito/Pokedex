package com.danilobrito.pokedex.ui.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.ui.adapter.PokemonAdapter
import com.danilobrito.pokedex.ui.view.fragments.PokedexFragment
import com.danilobrito.pokedex.util.NetworkResult
import com.danilobrito.pokedex.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        val transaction = supportFragmentManager.beginTransaction()
        val pokedexFragment = PokedexFragment()
        transaction.add(R.id.activity_container, pokedexFragment).commit()
    }
}