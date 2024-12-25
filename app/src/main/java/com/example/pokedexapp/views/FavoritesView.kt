package com.example.pokedexapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedexapp.components.PokemonCard
import com.example.pokedexapp.data.DataStore

@Composable
fun FavoritesView(
    dataStore: DataStore = hiltViewModel(),
    navController: NavController
) {
    // Collect the favorite Pokémon list from the store
    val favoritePokemonList by dataStore.favoritePokemonList.collectAsState(emptyList())

    // Snackbar host state for showing messages
    val snackbarHostState = remember { SnackbarHostState() }

    println("Favorites: $favoritePokemonList")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA))
            .padding(start = 10.dp, top = 60.dp)
    ) {
        // Title for Favourite Pokémon
        Text(
            text = "Your Favourite Pokémon's",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 6.dp)
        )

        // Snackbar Host for showing messages
        SnackbarHost(hostState = snackbarHostState)

        // Favourite Pokémon List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(favoritePokemonList.chunked(2)) { row ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(8.dp),
                    ) {
                        row.forEach { pokemon ->
                            PokemonCard(
                                pokemon = pokemon,
                                isFavorite = dataStore.isFavorite(pokemon),
                                onFavoriteToggle = {
                                    dataStore.toggleFavorite(pokemon)
                                }
                            ) {
                                navController.navigate("pokemonDetail/${pokemon.id}")
                            }
                        }
                    }
                }
            }
        }
    }
}