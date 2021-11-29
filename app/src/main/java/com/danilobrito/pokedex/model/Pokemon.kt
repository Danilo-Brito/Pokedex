package com.danilobrito.pokedex.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pokemon(
    var page: Int = 0,
    val name: String,
    val url: String
) : Parcelable {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}