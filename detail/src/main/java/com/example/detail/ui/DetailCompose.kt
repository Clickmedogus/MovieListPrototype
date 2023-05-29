package com.example.detail.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.detail.DetailViewmodel.DetailViewmodel
import kotlinx.coroutines.delay

@Composable
fun DetailCompose(peopleIndex: Int, navController: NavController) {

    val context = LocalContext.current
    val viewModel: DetailViewmodel = hiltViewModel()

    // Veriyi alma işlemi
    LaunchedEffect(Unit) {
        try {
            viewModel.fetchUserData() // Veri alımı
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        // Veri alımının tamamlanmasını beklemek için döngü
        while (viewModel.isLoading) {
            // Veriler henüz gelmedi, bekle
            delay(100) // Belirli bir süre bekler
        }
    }

    // Eğer veri alımı devam ediyorsa, CircularProgress ekranda görünmeye devam eder.
    if (!viewModel.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    // Veri alındıysa ve geçerli bir indeks ise, veriyi gönderir.
    val userData = viewModel.userData.collectAsState().value.data
    if (!viewModel.isLoading && userData != null && peopleIndex >= 0 && peopleIndex < userData.size) {
        val people = userData[peopleIndex]
        PeopleDetailItem(people, navController)
    }
}