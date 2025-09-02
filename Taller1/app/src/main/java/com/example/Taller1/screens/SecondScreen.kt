package com.example.Taller1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Triqui") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "retorno")
                    }
                }
            )
        }
    ) { p ->
        Triqui(Modifier.padding(p))
    }
}

@Composable
fun Triqui(modifier: Modifier = Modifier) {
    var tablero by remember { mutableStateOf(List(9) { "" }) }
    var turnoX by remember { mutableStateOf(true) }

    fun verificarGanador(tab: List<String>): String? {
        if (tab[0].isNotEmpty() && tab[0] == tab[1] && tab[0] == tab[2]) return tab[0]
        if (tab[3].isNotEmpty() && tab[3] == tab[4] && tab[3] == tab[5]) return tab[3]
        if (tab[6].isNotEmpty() && tab[6] == tab[7] && tab[6] == tab[8]) return tab[6]
        if (tab[0].isNotEmpty() && tab[0] == tab[3] && tab[0] == tab[6]) return tab[0]
        if (tab[1].isNotEmpty() && tab[1] == tab[4] && tab[1] == tab[7]) return tab[1]
        if (tab[2].isNotEmpty() && tab[2] == tab[5] && tab[2] == tab[8]) return tab[2]
        if (tab[0].isNotEmpty() && tab[0] == tab[4] && tab[0] == tab[8]) return tab[0]
        if (tab[2].isNotEmpty() && tab[2] == tab[4] && tab[2] == tab[6]) return tab[2]
        return null
    }

    fun tableroLleno(tab: List<String>): Boolean {
                for (i in 0..8) {
            if (tab[i].isEmpty()) {
                return false
            }
        }
        return true
    }

    val ganador = verificarGanador(tablero)
    val lleno = tableroLleno(tablero)


    val estado: String = if (ganador != null) {
        "Ganó $ganador"
    } else {
        if (lleno) {
            "Empate"
        } else {
            if (turnoX) "Turno: X" else "Turno: O"
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(estado, fontSize = 40.sp, textAlign = TextAlign.Center)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {


            for (fila in 0..2) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    for (columna in 0..2) {
                        val indice = fila * 3 + columna

                        Button(
                            onClick = {
                                if (tablero[indice].isEmpty() && ganador == null) {
                                    val siguiente = if (turnoX) "X" else "O"
                                    val nuevo = tablero.toMutableList()
                                    nuevo[indice] = siguiente
                                    tablero = nuevo
                                    turnoX = !turnoX
                                }
                            },
                            enabled = tablero[indice].isEmpty() && ganador == null,
                            modifier = Modifier.size(84.dp)
                        ) {
                            Text(tablero[indice], fontSize = 28.sp)
                        }
                    }
                }
            }
        }

        Button(
            onClick = {

                tablero = listOf("", "", "", "", "", "", "", "", "")
                turnoX = true
            }
        ) { Text("Reiniciar") }
    }
}
