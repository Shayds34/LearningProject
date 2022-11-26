package com.sederikkuapplication.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sederikkuapplication.features.project.presentation.ProjectViewModel

@Composable
fun ProjectSupply(viewModel: ProjectViewModel) {
    Column(
    ) {
        Text(
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp)
                .height(30.dp)
                .width(300.dp),
            text = "Project Supply",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        SupplyCard(viewModel = viewModel)
    }
}

@Composable
private fun SupplyCard(viewModel: ProjectViewModel) {
    val tokenDetailed = viewModel.projectUiState.token
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
            if (tokenDetailed != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Supply"
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        tokenDetailed.supply?.let {
                            Text(
                                text = it
                            )
                        }
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = rememberAsyncImagePainter(model = tokenDetailed.assets?.pngUrl),
                            contentDescription = null
                        )
                    }

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Circulating Supply"
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        tokenDetailed.circulatingSupply?.let {
                            Text(
                                text = it
                            )
                        }
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = rememberAsyncImagePainter(model = tokenDetailed.assets?.pngUrl),
                            contentDescription = null
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Burnt"
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        tokenDetailed.burnt?.let {
                            Text(
                                text = it
                            )
                        }
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = rememberAsyncImagePainter(model = tokenDetailed.assets?.pngUrl),
                            contentDescription = null
                        )
                    }
                }
            }
        }

    }
}