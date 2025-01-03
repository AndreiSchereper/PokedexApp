package nl.schereper.andrei.model

data class PokemonSpeciesResponse(
    val evolution_chain: EvolutionChainInfo
)

data class EvolutionChainInfo(
    val url: String
)
