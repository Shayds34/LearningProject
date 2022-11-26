package com.sederikkuapplication.presentation.mainboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sederikkuapplication.R
import com.sederikkuapplication.features.project.presentation.ProjectViewModel
import com.sederikkuapplication.shared.domain.models.TokenTeamAccount

@Composable
fun TeamAccountsList(viewModel: ProjectViewModel) {
    val lockedAccounts: List<TokenTeamAccount> = viewModel.projectUiState.lockedAccounts
    val itemcount = lockedAccounts.size
    if (itemcount > 0) {
        Column {
            Text(
                modifier = Modifier
                    .padding(start = 24.dp, top = 24.dp)
                    .height(30.dp)
                    .width(300.dp),
                text = "Project Wallets",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                items(itemcount) {
                    CardWallet(lockedAccounts[it])
                }
            }
        }

    }

}

@Composable
private fun CardWallet(account: TokenTeamAccount) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .requiredWidth(180.dp)
            .shadow(elevation = 10.dp)
            .padding(horizontal = 7.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .padding(7.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = account.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = rememberAsyncImagePainter(model = account.pngUrl),
                    contentDescription = null
                )
                Text(
                    fontSize = 12.sp,
                    text = account.balance)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                //Spacer(modifier = Modifier.size(15.dp))
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 5.dp),
                    painter = painterResource(id = R.drawable.ic_locked),
                    contentDescription = "Locked account"
                )
            }
        }

    }
}