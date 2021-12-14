package com.danilobrito.pokedex.ui.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.ui.view.fragments.PokedexFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val pokedexFragment = PokedexFragment()
        transaction.add(R.id.activity_container, pokedexFragment).commit()
    }
}