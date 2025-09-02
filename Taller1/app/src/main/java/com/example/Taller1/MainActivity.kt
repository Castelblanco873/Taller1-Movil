package com.example.Taller1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.Taller1.navigation.AppScreens
import com.example.Taller1.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var pressed by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        topBar = {MyTopBar()},
        bottomBar = {},

    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Button(onClick = {
                pressed=true
                showToast("Selecciono triqui",context)
                navController.navigate("${AppScreens.Second.name}/$name")

            }, modifier = Modifier.fillMaxWidth()) {
                Image(painterResource(R.drawable.triqui),"triqui")
            }


            Button(onClick = {
                pressed=true
                showToast("Selecciono F1",context)
                navController.navigate("${AppScreens.Third.name}/$name")

            }, modifier = Modifier.fillMaxWidth()) {
                Image(painterResource(R.drawable.f1img),"f1")
            }

        }

    }
}


fun showToast(message : String, context: Context){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(){
    CenterAlignedTopAppBar(
        title = {Text("Seleccione una opción")},

    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}

