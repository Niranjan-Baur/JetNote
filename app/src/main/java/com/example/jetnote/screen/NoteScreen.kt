package com.example.jetnote.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onRemoveNote: (Note) -> Unit,
    navController: NavController
){

    Column() {
        TopAppBar(modifier = Modifier.padding(0.dp),title = {
                          Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = Color(0xFFE8C1F1)
        )

        LazyColumn(modifier = Modifier.padding(6.dp)){
            items(notes){ note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
        FloatingActionButton(modifier = Modifier
            .padding(end = 20.dp, bottom = 20.dp)
            .size(60.dp)
            ,onClick = {
                 navController.navigate(route = Screen.AddNote.route)
            }
            ,shape = CircleShape) {
            Icon(imageVector = Icons.Rounded.Add , contentDescription = "Add Icon")
        }

    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked : (Note) -> Unit
){
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp))
            .fillMaxWidth(),
        color = Color(0xFF96CFF7),
        elevation = 6.dp,
    ) {
        Column(
            modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
            ){
            Text(text = note.title, style = MaterialTheme.typography.h6, color = Color.Black)
            Spacer(modifier.height(5.dp))
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier.height(5.dp))
            Text(text = note.entriesDate.format(DateTimeFormatter.ofPattern("HH:mm a - dd MMM, E")), fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onRemoveNote = {},
        navController = rememberNavController())
}


// next viewModel




















