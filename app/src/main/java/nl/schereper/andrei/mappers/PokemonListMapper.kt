package nl.schereper.andrei.mappers

import nl.schereper.andrei.model.Pokemon
import nl.schereper.andrei.model.PokemonListResponse
import javax.inject.Inject

class PokemonListMapper @Inject constructor() {
    fun mapPokemonList(response: PokemonListResponse): List<nl.schereper.andrei.model.Pokemon> {
        return response.results.map { pokemonResponse ->
            val id = extractPokemonIdFromUrl(pokemonResponse.url)
            nl.schereper.andrei.model.Pokemon(
                id = id,
                name = pokemonResponse.name,
                imageUrl = generatePokemonImageUrl(id)
            )
        }
    }

    private fun extractPokemonIdFromUrl(url: String): Int {
        return url.trimEnd('/').substringAfterLast('/').toInt()
    }

    private fun generatePokemonImageUrl(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }
}
