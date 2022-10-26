package com.kaisersakhi.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kaisersakhi.noteapp.ui.screens.MainScreen
import com.kaisersakhi.noteapp.ui.theme.Black
import com.kaisersakhi.noteapp.ui.theme.NoteAppTheme
import com.kaisersakhi.noteapp.viewmodel.MainScreenViewModel
import com.kaisersakhi.noteapp.viewmodel.MainScreenViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var mainScreenViewModel: MainScreenViewModel
        val viewModelFactory = MainScreenViewModelFactory(application)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Black)
            val mainScreenViewModel by viewModels<MainScreenViewModel>(factoryProducer = {viewModelFactory})
            NoteAppTheme {
                MainScreen(mainScreenViewModel, this)
            }
        }
    }
}

