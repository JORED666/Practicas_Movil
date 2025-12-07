package com.project.examenprctico.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<PokemonTypeSlot>,
    val abilities: List<PokemonAbilitySlot>,
    val stats: List<PokemonStatSlot>
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)

data class PokemonType(
    val name: String
)

data class PokemonAbilitySlot(
    val ability: PokemonAbility
)

data class PokemonAbility(
    val name: String
)

data class PokemonStatSlot(
    @SerializedName("base_stat") val baseStat: Int,
    val stat: PokemonStat
)

data class PokemonStat(
    val name: String
)
