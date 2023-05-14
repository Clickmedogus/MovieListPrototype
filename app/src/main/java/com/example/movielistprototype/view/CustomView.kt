package com.example.movielistprototype.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomView(
    backgroundColor: Color,
    text: String,
    textColor: Color,
    textSize: TextUnit,
    modifier: Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = modifier
            .fillMaxWidth())
    {
        Text(
            text = text,
            color = textColor,
            fontSize = textSize
            )
    }
}

@Preview
@Composable
fun PreviewCustomView() {
    CustomView(Color.LightGray,"Button", Color.Black,15.sp,Modifier)
}