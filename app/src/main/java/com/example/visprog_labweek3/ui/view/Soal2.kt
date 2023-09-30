package com.example.visprog_labweek3.ui.view

import androidx.compose.foundation.Image
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visprog_labweek3.R
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var hMeter by rememberSaveable { mutableStateOf("") }

    var isWeightValid by rememberSaveable { mutableStateOf(true) }
    var isHeightValid by rememberSaveable { mutableStateOf(true) }

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var bmiResult by rememberSaveable { mutableStateOf<Float?>(null) }
    var bmiCategory by rememberSaveable { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomTextWeightField(
            value = weight,
            onValueChange = {weight = it},
            text = "Weight in Kg",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            isWeightValid = isWeightValid
        )
        CustomTextHeightField(
            value = height,
            onValueChange = {height = it},
            text = "Height in Cm",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            isHeightValid = isHeightValid
        )
        Button(
            onClick = {
                val weight = weight.toFloatOrNull()
                val height = height.toFloatOrNull()

                if (weight != null && weight > 0) {
                    isWeightValid = true
                } else {
                    isWeightValid = false
                }

                if (height != null && height > 0) {
                    isHeightValid = true
                } else {
                    isHeightValid = false
                }

                if(isWeightValid && isHeightValid){
                    showDialog = true
                }else{
                    showDialog = false
                }

                val heightMeter = height?.div(100f)
                hMeter = heightMeter.toString()

                val bmi = weight!! / (heightMeter!! * heightMeter)
                bmiResult = bmi

                // Determine BMI category
                bmiCategory = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 24.9 -> "Normal"
                    bmi < 29.9 -> "Overweight"
                    else -> "Obese"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            enabled = height.isNotBlank() && weight.isNotBlank()
        ) {
            Text(text = "Calculate BMI", color = Color.White)
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = { Text("Your BMI Analysis") },
                text = {
                    Column {
                        Text("Your Height: $hMeter m")
                        Text("Your Weight: $weight kg")
                        Text("Your BMI Score: ${"%.2f".format(bmiResult)}")
                        Text("You are $bmiCategory weight")
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextHeightField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isHeightValid: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text, color = Color.Blue) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isHeightValid,
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Blue,
            focusedBorderColor = Color.Blue,
            textColor = Color.Blue,
            cursorColor = Color.Blue
        )
    )
    if (!isHeightValid) {
        Text(
            text = "Please submit the valid height greater than 0",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextWeightField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isWeightValid: Boolean
) {
    Image(
        painter = painterResource(id = R.drawable.baseline_health_and_safety_24),
        contentDescription = "Profile",
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text, color = Color.Blue) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isWeightValid,
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Blue,
            focusedBorderColor = Color.Blue,
            textColor = Color.Blue,
            cursorColor = Color.Blue
        )
    )
    if (!isWeightValid) {
        Text(
            text = "Please submit the valid weight greater than 0",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
        )
    }
}


