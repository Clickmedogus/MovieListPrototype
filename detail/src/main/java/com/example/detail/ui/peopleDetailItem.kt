package com.example.detail.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.detail.R
import com.example.detail.detailData.model.DetailPeople
import com.example.detail.ui.theme.Gray10

@Composable
fun PeopleDetailItem(people: DetailPeople, navController: NavController) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        val (tabBar) = createRefs()
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
                    url = "https://i.guim.co.uk/img/media/df6eba62024da3f69428980382dbe3281396ee69/326_296_5114_3068/master/5114.jpg?width=620&quality=45&dpr=2&s=none",
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                )
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
            selectedTabIndex = 1,
            isVisible = true
        )
    }

    BackPressed()
}

@Composable
fun BackPressed() {
    BackHandler {

    }
}

@Preview
@Composable
fun PeopleDetailItemPreview() {
    val people = DetailPeople(
        "DOĞUŞ İZGİ",
        "180",
        "100",
        "male",
        "blonde",
        "fair",
        "blue")


    PeopleDetailItem(people, rememberNavController())
}