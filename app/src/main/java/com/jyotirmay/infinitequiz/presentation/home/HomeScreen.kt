package com.jyotirmay.infinitequiz.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import com.jyotirmay.infinitequiz.navgraph.Route
import com.jyotirmay.infinitequiz.presentation.common.CustomOutlineButton
import com.jyotirmay.infinitequiz.ui.theme.Dimension

@Composable
fun HomeScreen(
    navController: NavController,
    selectedCountry: String,
    countries: List<CountryResponse>?,
    onCountrySelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2C2C)) // dark gray
            .padding(Dimension.padding_8),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Choose your country",
                color = Color.White,
                fontSize = 16.sp
            )

            Box {
                CustomOutlineButton(selectedCountry) {
                    expanded = true
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    countries?.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(country.name.common) },
                            onClick = {
                                onCountrySelected(country.name.common)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Text(
                text = "Use any public api to fetch this info",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomOutlineButton("Start") {
                navController.navigate(Route.QuizScreen.route)
            }

            CustomOutlineButton("Solve bookmarks") {
                navController.navigate(Route.BookmarkScreen.route)
            }
        }
    }
}