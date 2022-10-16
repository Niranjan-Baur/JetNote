package com.example.jetnote.screen

sealed class Screen(val route : String){
    object Note : Screen(route = "note_screen")
    object AddNote : Screen(route = "add_note_screen")
    object Splash : Screen(route = "splash_screen")
}
