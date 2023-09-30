package com.example.visprog_labweek3.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visprog_labweek3.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal4Preview() {
    var score1 by rememberSaveable { mutableStateOf("") }
    var score2 by rememberSaveable { mutableStateOf("") }
    var score3 by rememberSaveable { mutableStateOf("") }
    var avg by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }
    var isScore1Valid by rememberSaveable { mutableStateOf(true) }
    var isScore2Valid by rememberSaveable { mutableStateOf(true) }
    var isScore3Valid by rememberSaveable { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Student Score", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFFF7300),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_uc_round_full),
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(120.dp)
                    .clip(CircleShape)
            )
            CustomTextScoreField(
                value = score1,
                onValueChange = { score1 = it },
                text = "Richie's score",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                isScoreValid = isScore1Valid
            )
            CustomTextScoreField(
                value = score2,
                onValueChange = { score2 = it },
                text = "Michelle's score",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                isScoreValid = isScore2Valid
            )
            CustomTextScoreField(
                value = score3,
                onValueChange = { score3 = it },
                text = "Derend's score",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                isScoreValid = isScore3Valid
            )
            Button(
                onClick = {
                    isScore1Valid = score1.toInt() in 0..100
                    isScore2Valid = score2.toInt() in 0..100
                    isScore3Valid = score3.toInt() in 0..100

                    if(isScore1Valid && isScore2Valid && isScore3Valid) {
                        val tempAvg = (score1.toInt() + score2.toInt() + score3.toInt()) / 3.0
                        avg = String.format("%.6f", tempAvg)
                        if (tempAvg >= 70) {
                            message = "Siswa mengerti pembelajaran"
                        } else {
                            message = "Siswa perlu di beri soal tambahan"
                        }
                    }


                    if (isScore1Valid && isScore2Valid && isScore3Valid) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7300)
                ),
                enabled = score1.isNotBlank() && score2.isNotBlank() && score3.isNotBlank()
            ) {
                Text(
                    text = "Calculate Average",
                )

            }
            if (avg.isNotBlank()) {
                Text(
                    text = "Average Score : $avg",
                    modifier = Modifier
                        .border(1.dp, Color(0xFFFF7300), shape = RoundedCornerShape(50.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    color = Color(0xFFFF7300)
                )
            }
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextScoreField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isScoreValid: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text, color = Color(0xFFFF7300)) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isScoreValid,
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(0xFFFF7300),
            focusedBorderColor = Color(0xFFFF7300),
            textColor = Color(0xFFFF7300),
            cursorColor = Color(0xFFFF7300)
        )
    )
    if (!isScoreValid) {
        Text(
            text = "Please input score in range 0 - 100",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

