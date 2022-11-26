package com.sederikkuapplication.presentation.mainboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

@Composable
fun JexActivity(
    viewModel: ViewModel
) {
    Column(

    ) {
        Text(
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp)
                .height(30.dp)
                .width(300.dp),
            text = "JEXchange Activity",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        JexCard(viewModel = viewModel)
    }
}

@Composable
private fun JexCard(
    viewModel: ViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .shadow(elevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(text = "Hello")
            
        }
    }
}