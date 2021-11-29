package com.danilobrito.pokedex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.repository.PokedexRepository

class PokedexActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    private val viewModel by lazy {
        val repository = PokedexRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        init()
    }

    private fun init() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun fetchList(){
        viewModel
    }
}