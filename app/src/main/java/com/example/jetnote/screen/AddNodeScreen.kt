package com.example.jetnote.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.model.Note


@Composable
fun AddNoteScreen(onAddNote: (Note) -> Unit, navController: NavController){

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(modifier = Modifier.padding(0.dp),title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            backgroundColor = Color(0xFFE8C1F1)
        )

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

                Toast.makeText(context,"Note Added", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
            else{
                Toast.makeText(context,"Fields are required", Toast.LENGTH_SHORT).show()
            }
        })
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddNoteScreenPreview(){
    AddNoteScreen(onAddNote = {},navController = rememberNavController())
}