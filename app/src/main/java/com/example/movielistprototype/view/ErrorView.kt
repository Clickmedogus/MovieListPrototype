package com.example.movielistprototype.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movielistprototype.R

@Composable
fun ErrorView(
    modifier: Modifier,
    errorText : String,
    onClick : () ->Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(60.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_image2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(Color.White)
                .padding(60.dp)
                .size(300.dp)
        )

        Text(
            text = errorText,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .background(Color.White)
                .align(CenterHorizontally)
        )

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(text = "Try Again")
        }
    }
}

@Preview
@Composable
fun PreviewErrorView() {
    ErrorView(Modifier,"dsalkşdskaşad", onClick = { Unit })
}