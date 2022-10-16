package com.example.jetnote

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.screen.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    noteViewModel: NoteViewModel = viewModel()
){
    NavHost(navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(
            route = Screen.Note.route
        ){
            val notesList = noteViewModel.getAllNotes()

            NoteScreen(notes = notesList,
                onRemoveNote = {
                    noteViewModel.removeNote(it)
                },
                navController = navController
                )
        }
        composable(
            route = Screen.AddNote.route
        ){
            AddNoteScreen(onAddNote = {
                noteViewModel.addNote(it)
            },
                navController = navController)
        }
        composable(
            route = Screen.Splash.route
        ){
            AnimatedSplashScreen(navController)
        }
    }
}