package edu.unicauca.aplimovil.safekids

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.LoginScreen.name) {
        composable(route = Screens.LoginScreen.name){
            LoginScreen(onTeacherClick = {navController.navigate(Screens.DocenteScreen.name)}, onGuardianClick = {navController.navigate(Screens.AcudientesScreen.name)})
        }
        composable(route = Screens.DocenteScreen.name){
            DocenteScreen(onProfileClick = {navController.navigate(Screens.DocenteProfileScreen.name)})
        }
        composable(route = Screens.AcudientesScreen.name){
            AcudientesScreen(onProfileClick = {navController.navigate(Screens.AcudienteProfileScreen.name)})
        }
        composable(route = Screens.AcudienteProfileScreen.name){
            AcudienteProfileScreen()
        }
        composable(route = Screens.DocenteProfileScreen.name){
            DocenteProfileScreen()
        }
        composable(route = Screens.ActividadesScreen.name){
            ActividadScreen()
        }
    }

}


enum class Screens(){
    LoginScreen,
    DocenteScreen,
    AcudientesScreen,
    AcudienteProfileScreen,
    DocenteProfileScreen,
    ActividadesScreen
}