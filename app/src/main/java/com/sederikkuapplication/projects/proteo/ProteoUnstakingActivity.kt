package com.sederikkuapplication.projects.proteo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sederikkuapplication.projects.proteo.model.ProteoUnstakeModelUi
import com.sederikkuapplication.projects.proteo.viewmodel.ProteoUnstakingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProteoUnstakingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : ProteoUnstakingViewModel by viewModels()

        setContent {
                UnstakeList(viewModel = viewModel)
        }

    }
}

@Composable
fun UnstakeList(
    viewModel: ProteoUnstakingViewModel
) {
    viewModel.initialize()
    val unstakeList by remember { viewModel.unstakeList }

    LazyColumn(
        modifier = Modifier
            .padding(20.dp)
    ) {
        val itemCount = unstakeList.size
        item { 
            Text(
                text = "Liste des Unstakes"
            )
        }
        item { 
            Text(text = "$itemCount")
        }
        items(itemCount) {
            UnstakeCard(unstakeList[it])
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun UnstakeCard(unstake: ProteoUnstakeModelUi) {
    var withdrawBackground = Color.White
    var withdrawText = ""

    if (unstake.isWithdraw) {
        withdrawBackground = Color.Cyan
        withdrawText = "Already withdraw"
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        backgroundColor = withdrawBackground
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = unstake.shortenAddress)
                Text(text = unstake.sProteo + " sPROTEO" )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = unstake.epoch)
                Text(text = withdrawText)

            }
        }


    }
}