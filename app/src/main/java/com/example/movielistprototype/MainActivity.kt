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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.ui.theme.LightBlue
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
                .background(LightBlue)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .height(40.dp)
            )  {
                Text(
                    text = "User Live Data",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            OutlinedTextField(
                value = searchData.value,
                onValueChange = { searchData.value = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            ShowPeopleItemList(
                viewModel = viewModel,
                navController = navController,
                searchData = searchData.value
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ShowPeopleItemList(
    viewModel: PeopleViewModel,
    navController: NavController,
    searchData: String
) {
    //called the data
    val data = getData(viewModel, searchData)

    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        items(data.size) { i ->
            PeopleListItem(data[i]) { people ->
                navController.navigate("secondScreen/${i}")
            }
        }
    }
}

@Composable
private fun getData(
    viewModel: PeopleViewModel,
    searchData: String,
): List<People> {
    val context = LocalContext.current
    val filteredData: State<List<People>>
    //Calls the data from the viewModel. It prints the successful or unsuccessful status of the call as a toast message.
    LaunchedEffect(suspend { viewModel.getUserData() }) {
        val result = viewModel.getUserData()

        if (result is Resource.Success<*>) {
            Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
        } else if (result is Resource.Error<*>) {
            Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    //If a name is entered in the search bar, it filters the data accordingly.
    if (searchData.isEmpty()) {
        filteredData  = viewModel.getUserData.collectAsState()
    } else {
        filteredData = viewModel.getUserData.collectAsState().value.filter { it.name.contains(searchData, ignoreCase = true) }.let { filteredList ->
            remember { mutableStateOf(filteredList) }
        }
    }
    //If data retrieval is in progress, CircularProgress continues to run on the screen.
    if (!viewModel.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    // If data is received, it sends the data.
    if (viewModel.isLoading.value) {
        filteredData.value.let { data ->
            return data
        }
    }
    // If no data, it sends the emptyList.
    return emptyList()
}

@ExperimentalMaterialApi
@Composable
fun SecondScreen(peopleIndex: Int, viewModel: PeopleViewModel = hiltViewModel()) {
    val context = LocalContext.current
    //Calls the data from the viewModel. It prints the successful or unsuccessful status of the call as a toast message.
    LaunchedEffect(suspend { viewModel.getUserData() }) {
        val result = viewModel.getUserData()

        if (result is Resource.Success<*>) {
            Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
        } else if (result is Resource.Error<*>) {
            Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    //If data retrieval is in progress, CircularProgress continues to run on the screen.
    if (!viewModel.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    // If data is received, it sends the data.
    if (viewModel.isLoading.value) {
        val people = viewModel.getUserData.collectAsState().value.get(peopleIndex)

        PeopleDetailItem(people)
    }
}

