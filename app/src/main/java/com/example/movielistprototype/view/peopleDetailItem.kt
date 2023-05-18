package com.example.movielistprototype.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.movielistprototype.R
import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.interfaces.ImageLoaderInterface
import com.example.movielistprototype.ui.theme.Gray10

@Composable
fun PeopleDetailItem(people: People, navController: NavController, imageLoaderInterface: ImageLoaderInterface) {

    var painter: Painter? by remember { mutableStateOf(null) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        val (content, tabBar) = createRefs()
        Column(
            modifier = Modifier
                .padding(60.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(30.dp)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 30.dp)
            )
            {
                LoadImage(
                    url = "https://i.guim.co.uk/img/media/df6eba62024da3f69428980382dbe3281396ee69/326_296_5114_3068/master/5114.jpg?width=465&quality=85&dpr=1&s=none",
                    modifier = Modifier
                        .background(Gray10)
                        .padding(8.dp)
                        .size(110.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                    imageLoaderInterface = imageLoaderInterface
                ) { loadedPainter ->
                    painter = loadedPainter
                }

                Box(modifier = Modifier.size(200.dp)) {
                    if (painter != null) {
                        Image(
                            painter = painter!!,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = people.name,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Card(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(Gray10)
            ) {


                Row {
                    Image(painter = painterResource(id = R.drawable.gender),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)) {

                        Text(
                            text = "Gender",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = FontFamily.Default
                        )

                        Text(
                            text = people.gender,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }
                }
            }

            Card(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(Gray10)
            ) {


                Row {
                    Image(painter = painterResource(id = R.drawable.height_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)) {

                        Text(
                            text = "Height",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = FontFamily.Default
                        )

                        Text(
                            text = "${people.height} cm",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }
                }
            }

            Card(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(Gray10)
            ) {


                Row {
                    Image(painter = painterResource(id = R.drawable.mass_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)) {

                        Text(
                            text = "Mass",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = FontFamily.Default
                        )

                        Text(
                            text = "${people.mass} kg",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }
                }
            }

            Card(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(Gray10)
            ) {


                Row {
                    Image(painter = painterResource(id = R.drawable.hair_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)) {

                        Text(
                            text = "Hair Color",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = FontFamily.Default
                        )

                        Text(
                            text = people.hairColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }
                }
            }

            Card(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(Gray10)
            ) {


                Row {
                    Image(painter = painterResource(id = R.drawable.eye_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)) {

                        Text(
                            text = "Eye Color",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = FontFamily.Default
                        )

                        Text(
                            text = people.eyeColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }
                }
            }

            Card(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(Gray10)
            ) {


                Row {
                    Image(painter = painterResource(id = R.drawable.skin_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)) {

                        Text(
                            text = "Skin Color",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = FontFamily.Default
                        )

                        Text(
                            text = people.skinColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }
                }
            }
        }

        TabBar(
            backgroundColor = Gray10,
            firstTabText = "MainScreen",
            secondTabText = "DetailScreen",
            modifier = Modifier
                .constrainAs(tabBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            navController = navController,
            navigateIndex = 1,
            selectedTabIndex = 1
        )
    }
}

@Preview
@Composable
fun PeopleDetailItemPreview() {
    val people = People(
        "DOĞUŞ İZGİ",
        "180",
        "100",
        "male",
        "blonde",
        "fair",
        "blue")

    //PeopleDetailItem(people, rememberNavController(),ImageLoaderInterface)
}