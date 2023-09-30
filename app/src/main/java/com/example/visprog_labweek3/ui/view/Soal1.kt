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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.visprog_labweek3.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1Preview() {
    var base by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var area by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.outline_play_arrow_24),
            contentDescription = "Profile Picdt",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .rotate(270f)
        )
        CustomTextField(
            value = base,
            onValueChange = { base = it },
            text = "Base",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Blue,
            unfocuedBorderColor = Color.Blue,
            focusedBorderColor = Color.Blue,
            textColor = Color.Blue,
            cursorColor = Color.Blue
        )
        CustomTextField(
            value = height,
            onValueChange = { height = it },
            text = "Height",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.Blue,
            unfocuedBorderColor = Color.Blue,
            focusedBorderColor = Color.Blue,
            textColor = Color.Blue,
            cursorColor = Color.Blue
        )
        Text(
            text = "The Triangel Area is :",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        )
        Text(
            text = "$area",
            style = TextStyle(
                fontSize = 35.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
    val baseValue = base.toDoubleOrNull()
    val heightValue = height.toDoubleOrNull()
    if (baseValue != null && heightValue != null) {
        val triangleArea = 0.5 * baseValue * heightValue
        area = String.format("%.1f", triangleArea)
    } else {
        area = "0,0"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    color: Color,
    unfocuedBorderColor: Color,
    focusedBorderColor: Color,
    textColor : Color,
    cursorColor: Color
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text, color = color) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = unfocuedBorderColor,
            focusedBorderColor = focusedBorderColor,
            textColor = textColor,
            cursorColor = cursorColor
        )
    )
}