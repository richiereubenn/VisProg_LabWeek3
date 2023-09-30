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
fun Soal3Preview() {
    var name by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var isYearValid by remember { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Age Calculator", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFFF3D57),
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
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_3_24),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            CustomTextField(
                value = name,
                onValueChange = { name = it },
                text = "Enter your name",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = Color(0xFFFF3D57),
                unfocuedBorderColor = Color(0xFFFF3D57),
                focusedBorderColor = Color(0xFFFF3D57),
                textColor = Color(0xFFFF3D57),
                cursorColor = Color(0xFFFF3D57)
            )
            CustomTextYearField(
                value = year,
                onValueChange = { year = it },
                text = "Enter your birth year",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                isYearValid = isYearValid
            )
            Button(
                onClick = {
                    if (year.toIntOrNull() != null && year.toInt() <= 2023) {
                        isYearValid = true
                        val ageDifference = 2023 - year.toInt()
                        age = ageDifference.toString()
                    } else {
                        isYearValid = false
                    }

                    if (isYearValid) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                "Hi, $name! Your age is $age years"
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF3D57)
                ),
                enabled = name.isNotBlank() && year.isNotBlank()
            ) {
                Text(
                    text = "Calculate your age",
                )

            }
            if (isYearValid && age.isNotBlank()) {
                Text(
                    text = "Hi, $name! Your age is $age years",
                    modifier = Modifier
                        .border(1.dp, Color(0xFFFF3D57), shape = RoundedCornerShape(50.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    color = Color(0xFFFF3D57)
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
fun CustomTextYearField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isYearValid: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text, color = Color(0xFFFF3D57)) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isYearValid,
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(0xFFFF3D57),
            focusedBorderColor = Color(0xFFFF3D57),
            textColor = Color(0xFFFF3D57),
            cursorColor = Color(0xFFFF3D57)
        )
    )
    if (!isYearValid) {
        Text(
            text = "Please input true year",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}