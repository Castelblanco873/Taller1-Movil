package com.example.Taller1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Taller1.HomeScreen
import com.example.Taller1.screens.SecondScreen
import com.example.Taller1.screens.ThirdScreen

enum class AppScreens{
    Home,
    Second,
    Third,
    Fourth
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController =navController, startDestination = AppScreens.Home.name)  {
        composable(route = AppScreens.Home.name){
            HomeScreen(navController)
        }
        composable(route = "${AppScreens.Second.name}/{name}"){ backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            SecondScreen(navController)
        }
        composable(route = "${AppScreens.Third.name}/{name}"){ backStackEntry ->
            val name = backStackEntry.arguments?.getString("name2")
            ThirdScreen(navController)
        }

    }
}


