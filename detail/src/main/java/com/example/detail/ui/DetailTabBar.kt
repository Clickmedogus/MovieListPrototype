package com.example.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.detail.ui.theme.Gray10

@Composable
fun TabBar(
    backgroundColor: Color,
    firstTabText: String,
    secondTabText: String,
    modifier: Modifier,
    navController : NavController,
    navigateIndex : Int,
    selectedTabIndex : Int,
    isVisible : Boolean
) {

    var tabIndex by remember { mutableStateOf(0) }
    tabIndex = selectedTabIndex

    Column(
        modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(RoundedCornerShape(10.dp))
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier.height(50.dp),
            backgroundColor = backgroundColor
        ) {
            // İlk sekme tıklandığında "MainScreen" hedefine yönlendirilir.
            Tab(selected = tabIndex == 0, onClick = {
                if (tabIndex != 0 && isVisible) {
                    tabIndex = 0
                    navController.navigate("MainScreen")
                }
            }) {
                Text(firstTabText)
            }
            // İkinci sekme tıklandığında "secondScreen" hedefine yönlendirilir.
            Tab(selected = tabIndex == 1, onClick = {
                if(tabIndex != 1 && isVisible) {
                    tabIndex = 1
                    navController.navigate("secondScreen/${navigateIndex}")
                }
            }) {
                Text(secondTabText)
            }
        }
    }
}

@Preview
@Composable
fun PreviewTabBar() {
    TabBar(Gray10,"MainScreen","DetailScreen",Modifier, rememberNavController(),1,2,true)
}