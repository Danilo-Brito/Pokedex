package com.danilobrito.pokedex.util

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import com.danilobrito.pokedex.R

class TypeColor() {

    fun setColorType(color: String, button: AppCompatButton, context: Context) {

        when(color) {
            "normal" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.normal)!!
            }
            "fire" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.fire)!!
            }
            "flying" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.flying)!!
            }
            "ghost" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.ghost)!!
            }
            "ice" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.ice)!!
            }
            "poison" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.poison)!!
            }
            "psychic" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.psychic)!!
            }
            "rock" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.rock)!!
            }
            "steel" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.steel)!!
            }
            "water" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.water)!!
            }
            "fighting" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.fighting)!!
            }
            "grass" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.grass)!!
            }
            "ground" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.ground)!!
            }
            "bug" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.bug)!!
            }
            "dark" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.dark)!!
            }
            "dragon" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.dragon)!!
            }
            "electric" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.electric)!!
            }
            "fairy" -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.fairy)!!
            }
        }
    }
}