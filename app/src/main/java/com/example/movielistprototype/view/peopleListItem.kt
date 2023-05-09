package com.example.movielistprototype.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun PeopleListItem(people: People, onClick: (People) -> Unit) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable{onClick(people)}
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(Gray10)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row{

                Image(
                    painter = painterResource(id = R.drawable.human),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(110.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                )

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)) {
                    Text(
                        text = people.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "Gender: ${people.gender}",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun PeopleListItemPreview() {
    val people = People(
        name = "DOĞUŞ İZGİ",
        height = "180 cm",
        mass = "100 kg",
        gender = "male",
        hairColor = "black"
    )
    PeopleListItem(people = people) {}
}