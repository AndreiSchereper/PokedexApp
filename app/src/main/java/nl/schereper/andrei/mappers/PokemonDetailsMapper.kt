package nl.schereper.andrei.mappers

import nl.schereper.andrei.model.Pokemon
import nl.schereper.andrei.model.PokemonDetailsResponse
import javax.inject.Inject

class PokemonDetailsMapper @Inject constructor() {
    fun mapPokemonDetails(id: Int, response: PokemonDetailsResponse): nl.schereper.andrei.model.Pokemon {
        return nl.schereper.andrei.model.Pokemon(
            id = id,
            name = response.name,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png", // Basic sprite image URL
            baseExperience = response.base_experience,
            height = response.height,
            weight = response.weight,
            types = response.types.map { it.type.name }, // Map type names
            abilities = response.abilities.map { it.ability.name }, // Map ability names
            stats = response.stats.associate {
                it.stat.name.capitalize() to it.base_stat // Map stats with name as key and base_stat as value
            }
        )
    }
}