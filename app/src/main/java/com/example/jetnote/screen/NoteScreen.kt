package com.example.jetnote.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
            Icon(imageVector = Icons.Rounded.Notifications , contentDescription = "Icon")
        },
        backgroundColor = Color(0xFFD1D1D1)
        )
        
        // Content
        
        Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 9.dp),text = title, label = "Title", onTextChange = {
                if(it.all{char ->
                        char.isLetter() || char.isWhitespace() || char.isDigit()
                    }) title = it
            })
            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 9.dp),text = description, label = "Add a note", onTextChange = {
                if(it.all{char ->
                        char.isLetter() || char.isWhitespace() || char.isDigit()
                    }) description = it
            })
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()){
                    onAddNote(Note(title = title ,description = description))
                    title = " "
                    description = " "

                    Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                }
            })
        }

        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn{
            items(notes){ note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
            }
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
        color = Color(0xFFDFE6EB),
        elevation = 6.dp,
    ) {
        Column(
            modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
            ){
            Text(text = note.title, style = MaterialTheme.typography.h6)
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
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}



// next viewModel




















