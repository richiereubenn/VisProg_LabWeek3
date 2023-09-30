package com.example.visprog_labweek3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.visprog_labweek3.ui.theme.VisProg_LabWeek3Theme
import com.example.visprog_labweek3.ui.view.Soal1Preview
import com.example.visprog_labweek3.ui.view.Soal2Preview
import com.example.visprog_labweek3.ui.view.Soal3Preview
import com.example.visprog_labweek3.ui.view.Soal4Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisProg_LabWeek3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Soal4Preview()
                }
            }
        }
    }
}
