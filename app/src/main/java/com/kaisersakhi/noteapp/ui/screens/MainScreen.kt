package com.kaisersakhi.noteapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.kaisersakhi.noteapp.data.model.Note
import com.kaisersakhi.noteapp.ui.components.NoteListItem
import com.kaisersakhi.noteapp.ui.theme.LobsterFamily
import com.kaisersakhi.noteapp.ui.theme.TrispaceFamily
import com.kaisersakhi.noteapp.viewmodel.MainScreenViewModel

const val TAG = "MainScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(viewModel: MainScreenViewModel, context: Context) {
//    val selectedNote by viewModel.selectedNote.observeAsState()

    var titleState by remember {
        mutableStateOf("")
    }
    var contentState by remember {
        mutableStateOf("")
    }
    var currentNoteId = 0

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Self Note", fontFamily = LobsterFamily)
                },
                elevation = 10.dp,
            )
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {


            UserNote(
                title = titleState,
                content = contentState,
                onTitleChange = {
                    titleState = it
                },
                onContentChange = {
                    contentState = it
                },
                onAdd = {
                    if (titleState.isNotEmpty() || contentState.isNotEmpty())
                        viewModel.insertNote(Note(0, titleState, contentState))
                    titleState = ""
                    contentState = ""
                },
                onUpdate = {
                    viewModel.updateNote(Note(currentNoteId, titleState, contentState))
                    titleState = ""
                    contentState = ""
                })
            Divider()
            SavedNotes(viewModel, onItemClick = { it ->
                //onSavedItemClick, get the note from the database
                //then update the editor with the retrived note
                viewModel.getNote(it)
                viewModel.selectedNote.observe(context as LifecycleOwner) {
                    it?.let { note ->
                        titleState = note.noteTitle
                        contentState = note.noteContent
                        currentNoteId = note.id
                    }
                }
            })
        }

    }
}

@Composable
fun UserNote(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onAdd: () -> Unit,
    onUpdate: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(0.90f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //title textField
        OutlinedTextField(
            value = title,
            onValueChange = {
                onTitleChange(it)
            },
            label = {
                Text("Title")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = content,
            onValueChange = {
                onContentChange(it)
            },
            label = {
                Text(text = "Note")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Row {

            Button(
                onClick = {
                    onAdd()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Add +", fontFamily = TrispaceFamily, fontSize = 30.sp)
            }

            Button(
                onClick = {
                    onUpdate()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Update", fontFamily = TrispaceFamily, fontSize = 30.sp)
            }
        }

    }
}

@Composable
fun SavedNotes(viewModel: MainScreenViewModel, onItemClick: (Int) -> Unit) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(notes) { note ->
            NoteListItem(note = note,
                onItemClick = {
                    //generate an event to get note
//                    viewModel.getNote(it)
                    onItemClick(it)
                }, onDelete = {
                    viewModel.deleteNote(it)
                })
        }
    }
}