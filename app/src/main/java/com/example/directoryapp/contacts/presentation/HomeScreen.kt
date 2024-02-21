package com.example.directoryapp.contacts.presentation

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.directoryapp.common.Constants

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello User!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate(Constants.CONTACT_LIST_SCREEN)
            },
            modifier = Modifier.padding(bottom = 16.dp),
            shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
        ) {
            Text(text = "People")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Constants.ROOM_LIST_SCREEN)
            },
            modifier = Modifier.padding(bottom = 16.dp),
            shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
        ) {
            Text(text = "Rooms")
        }
    }
}