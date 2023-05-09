package com.example.movielistprototype.view

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
import com.example.movielistprototype.R
import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.ui.theme.Gray10

@Composable
fun PeopleDetailItem(people: People) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray)
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,),
        colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
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

                    Image(
                        painter = painterResource(id = R.drawable.human),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .background(Gray10)
                            .padding(8.dp)
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

    PeopleDetailItem(people)
}