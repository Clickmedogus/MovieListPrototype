package com.example.movielistprototype

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movielistprototype.ui.theme.MovieListPrototypeTheme
import com.example.movielistprototype.utils.Resource
import com.example.movielistprototype.view.PeopleDetailItem
import com.example.movielistprototype.view.PeopleListItem
import com.example.movielistprototype.viewmodel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListPrototypeTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "CallApi") {
                    composable("CallApi") {
                        MainScreen(navController = navController)
                    }
                    composable(
                        "secondScreen/{peopleIndex}",
                        arguments = listOf(navArgument("peopleIndex") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val peopleIndex = backStackEntry.arguments?.getInt("peopleIndex") ?: 0
                        SecondScreen(peopleIndex)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun MainScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val viewModel: PeopleViewModel = hiltViewModel()
    val searchData = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(15.dp)
            ) {
                Text(
                    text = "User Live Data",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            TextField(
                value = searchData.value,
                onValueChange = { searchData.value = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            CallApi(
                viewModel = viewModel,
                navController = navController,
                searchData = searchData.value
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CallApi(
    viewModel: PeopleViewModel,
    navController: NavController,
    searchData: String
) {
    val context = LocalContext.current
    LaunchedEffect(suspend { viewModel.getUserData() }) {
        val result = viewModel.getUserData()

        if (result is Resource.Success) {
            Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
        } else if (result is Resource.Error) {
            Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    val filteredData = if (searchData.isEmpty()) {
        viewModel.getUserData.value
    } else {
        viewModel.getUserData.value?.filter { it.name.contains(searchData, ignoreCase = true) }
    }

    if (!viewModel.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    if (viewModel.isLoading.value) {
        filteredData?.let { data ->
            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ) {
                items(data.size) { index ->
                    PeopleListItem(data[index]) { people ->
                        navController.navigate("secondScreen/$index")
                    }
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun SecondScreen(peopleIndex: Int, viewModel: PeopleViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(suspend { viewModel.getUserData() }) {
        val result = viewModel.getUserData()

        if (result is Resource.Success) {
            Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
        } else if (result is Resource.Error) {
            Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    if (!viewModel.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    if (viewModel.isLoading.value) {
        val people = viewModel.getUserData.value?.get(peopleIndex)

        if (people != null) {
            PeopleDetailItem(people)
        } else {
            Toast.makeText(context, "Error: People Null", Toast.LENGTH_LONG)
                .show()
        }
    }
}

