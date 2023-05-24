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
import androidx.compose.runtime.MutableState
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.interfaces.ImageLoaderInterface
import com.example.movielistprototype.ui.theme.Gray10
import com.example.movielistprototype.ui.theme.MovieListPrototypeTheme
import com.example.movielistprototype.view.ErrorView
import com.example.movielistprototype.view.item.PeopleDetailItem
import com.example.movielistprototype.view.item.PeopleListItem
import com.example.movielistprototype.view.TabBar
import com.example.movielistprototype.viewmodel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    @NotNull
    lateinit var imageLoaderInterface: ImageLoaderInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListPrototypeTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "MainScreen") {
                    composable("MainScreen") {
                        MainScreen(navController = navController)
                    }
                    composable(
                        "secondScreen/{peopleIndex}",
                        arguments = listOf(navArgument("peopleIndex") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val peopleIndex = backStackEntry.arguments?.getInt("peopleIndex") ?: 0
                        SecondScreen(peopleIndex, navController,imageLoaderInterface)
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
    val isVisible = remember { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val (header, searchField, peopleItemList, customView) = createRefs()

            Text(
                text = "User Live Data",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray,
                modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            ShowPeopleItemList(
                viewModel = viewModel,
                navController = navController,
                searchData = searchData.value,
                modifier = Modifier.constrainAs(peopleItemList) {
                    top.linkTo(searchField.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(customView.top, margin = 10.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
                isVisible
            )

            if (isVisible.value) {

                TabBar(
                    backgroundColor = Gray10,
                    firstTabText = "MainScreen",
                    secondTabText = "DetailScreen",
                    modifier = Modifier.constrainAs(customView) {
                        start.linkTo(parent.start, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                    },
                    navController = navController,
                    navigateIndex = 1,
                    selectedTabIndex = 0
                )

                OutlinedTextField(
                    value = searchData.value,
                    onValueChange = { searchData.value = it },
                    label = { Text("Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .constrainAs(searchField) {
                            top.linkTo(header.bottom, margin = 10.dp)
                            start.linkTo(parent.start, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                        },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Gray10,
                        cursorColor = Color.White,
                        disabledLabelColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            } else
                ErrorView(modifier = Modifier, errorText = "Error") {
                    isVisible.value =true
                }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ShowPeopleItemList(
    viewModel: PeopleViewModel,
    navController: NavController,
    searchData: String,
    modifier: Modifier,
    isVisible : MutableState<Boolean>
) {

    // Hata yoksa, veriyi göster
    val data = getData(viewModel, searchData,isVisible)

    LazyColumn(
        modifier = modifier.padding(10.dp)
    ) {
        items(data.size) { i ->
            PeopleListItem(data[i]) {
                navController.navigate("secondScreen/${i}")
            }
        }
    }
}


@Composable
private fun getData(
    viewModel: PeopleViewModel,
    searchData: String,
    isVisible: MutableState<Boolean>
): List<People> {
    val context = LocalContext.current
    val filteredData: List<People>

    // Veriyi alma işlemi
    LaunchedEffect(Unit) {
        try {
            viewModel.fetchUserData() // Veri alımını başlat
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            isVisible.value = false
        }

        // Veri alımının tamamlanmasını beklemek için döngü kullanabilirsiniz
        while (viewModel.isLoading) {
            // Veriler henüz gelmedi, bekle
            delay(100) // Belirli bir süre bekleyin (100 milisaniye örneği)
        }

        // Veri yüklendiyse, isVisible değerini güncelleyin
        if (viewModel.userData.value.data?.isNotEmpty() == true) {
            isVisible.value = true
        }
    }
    // Eğer veri yüklemesi devam ediyorsa, CircularProgress ekranda görünmeye devam eder.
    if (viewModel.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    //If a name is entered in the search bar, it filters the data accordingly.
    filteredData = if (searchData.isEmpty()) {
        viewModel.userData.collectAsState().value.data.orEmpty()
    } else {
        viewModel.userData.collectAsState().value.data.orEmpty().filter { it.name.contains(searchData, ignoreCase = true) }
    }

    if (filteredData.isEmpty())
        isVisible.value = false


    return filteredData
}

@ExperimentalMaterialApi
@Composable
fun SecondScreen(peopleIndex: Int, navController: NavController, imageLoaderInterface: ImageLoaderInterface, viewModel: PeopleViewModel = hiltViewModel()) {
    val context = LocalContext.current
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
        PeopleDetailItem(people, navController,imageLoaderInterface)
    }
}



