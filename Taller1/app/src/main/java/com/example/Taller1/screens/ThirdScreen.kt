package com.example.Taller1.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.Taller1.data.loadRemotePilotosFromAssets
import com.example.Taller1.model.RemotePiloto
import com.example.Taller1.model.RemotePilotoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(navController: NavController) {
    val contexto = androidx.compose.ui.platform.LocalContext.current
    var pilotos by remember { mutableStateOf(RemotePiloto()) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            pilotos = loadRemotePilotosFromAssets(contexto)
            error = null
        } catch (e: Exception) {
            error = e.message ?: "Error cargando F1.json"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pilotos Fórmula 1",
                        modifier = Modifier.padding(10.dp),

                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "retorno")
                    }
                }


            )
        }
    ) { padding ->
        if (error != null) {
            Text(
                text = "Error: $error",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            )
        } else {
            MyLazyList(
                pilotos = pilotos,
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }
}

@Composable
fun MyLazyList(
    pilotos: List<RemotePilotoItem>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .clipToBounds()
            .background(Color(0xFFF4FF69)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(pilotos) { piloto ->

            val headshot = piloto.headshot_url
            val fullName = piloto.full_name
            val teamName = piloto.team_name
            val teamColour = piloto.team_colour
            val acronym = piloto.name_acronym
            val country = piloto.country_code ?: ""

            val route = buildString {
                append("piloto/")
                append(Uri.encode(headshot)); append("/")
                append(Uri.encode(fullName)); append("/")
                append(Uri.encode(teamName)); append("/")
                append(Uri.encode(teamColour)); append("/")
                append(Uri.encode(acronym)); append("/")
                append(Uri.encode(country))
            }

            Text(
                text = " ${piloto.driver_number}. $fullName",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate(route) }
            )
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}
