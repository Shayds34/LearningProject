package com.sederikkuapplication.features.home.presentation

import android.os.SystemClock
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sederikkuapplication.R
import com.sederikkuapplication.features.home.domain.models.Token
import com.sederikkuapplication.features.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.sql.Date
import java.text.SimpleDateFormat

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Timer(viewModel)
        SearchBar(viewModel, "Search ...")
        ProjectFavories(navController, viewModel)
    }
}

@Composable
fun SearchBar(
    viewModel: HomeViewModel,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf( hint != "")
    }
    Box(
        Modifier.padding(20.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.LightGray),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.DarkGray, CircleShape)
                .padding(10.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.hasFocus
                }
                .focusable()
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(start = 20.dp, top = 9.dp)
            )
        }
    }
}

@Composable
fun Timer(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val price = viewModel.uiState.stats?.price
    val epoch = viewModel.uiState.stats?.epoch
    val current = System.currentTimeMillis()
    var size by remember { mutableStateOf(IntSize.Zero) }
    var ratio: Float by remember { mutableStateOf(0.toFloat()) }

    var start = 0.toLong()
    var end = 0.toLong()

    if (epoch != null) {
        start = epochToMillis(epoch.toLong())
        end = epochToMillis(epoch.toLong() + 1)
        ratio = (current - start).toFloat() / (end - start).toFloat()
    }

    Box(
        modifier = modifier.onSizeChanged { size = it },
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = modifier.size(180.dp),
        ) {
            val sizeOuterArc = Size(size.width.toFloat() + 50, size.height.toFloat() + 50)
            val sizeArc = Size(size.width.toFloat(), size.height.toFloat())
            val sizeInnerArc = Size(size.width.toFloat() - 50, size.height.toFloat() - 50)


            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(
                    x = (size.width - sizeOuterArc.width) / 2,
                    y = (size.height - sizeOuterArc.height) / 2
                ),
                size = sizeOuterArc,
                style = Stroke(2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.DarkGray,
                startAngle = 270f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(
                    x = (size.width - sizeArc.width) / 2,
                    y = (size.height - sizeArc.height) / 2
                ),
                size = sizeArc,
                style = Stroke(7.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color(0xFFB71C1C),
                startAngle = 270f,
                sweepAngle = 360f * ratio,
                useCenter = false,
                topLeft = Offset(
                    x = (size.width - sizeArc.width) / 2,
                    y = (size.height - sizeArc.height) / 2
                ),
                size = sizeArc,
                style = Stroke(7.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.DarkGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(
                    x = (size.width - sizeInnerArc.width) / 2,
                    y = (size.height - sizeInnerArc.height) / 2
                ),
                size = sizeInnerArc,
                style = Stroke(1.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (epoch != null) {
                val initialMillis = epochToMillis(epoch.toLong() + 1) - current
                val timeleft by rememberCountdownTimerState(
                    initialMillis = initialMillis
                )
                Text(
                    text = "EPOCH",
                    color = Color.Gray,
                    fontSize = 20.sp
                )
                Text(
                    text = "$epoch",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "next epoch in",
                    color = Color.Gray
                )
                Text(text = millisDateFormat(timeleft))
            }
        }

    }

}

fun epochToMillis(epoch: Long) : Long {
    val epochZeroMillis = 1596122100
    return (epochZeroMillis + (epoch * 86400)) * 1000
}

fun millisDateFormat(unixMillis: Long): String {
    val dateFormat = SimpleDateFormat("H:mm:ss")
    val date = Date(unixMillis)
    return dateFormat.format(date)
}


@Composable
fun rememberCountdownTimerState(
    initialMillis: Long,
    step: Long = 1000
): MutableState<Long> {
    val timeLeft = remember { mutableStateOf(initialMillis) }
    LaunchedEffect(initialMillis, step) {
        val startTime = SystemClock.uptimeMillis()
        while(isActive && timeLeft.value > 0) {
            val duration = (SystemClock.uptimeMillis() - startTime).coerceAtLeast(0)
            timeLeft.value = (initialMillis - duration).coerceAtLeast(0)
            delay(step)
        }
    }
    return timeLeft
}

@Composable
fun ProjectFavories(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val tokens = viewModel.uiState.tokens
    val cyber: Token? = tokens.find {
        it.identifier == "CYBER-489c1c"
    }
    val proteo: Token? = tokens.find {
        it.identifier == "PROTEO-0c7311"
    }
    Column(
        Modifier.padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Following Projects",
            modifier = Modifier.padding(bottom = 5.dp),
            fontSize = 20.sp
        )
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            item {
                if (cyber != null) {
                    ProjectCard(navController, cyber)
                }
            }
            item {
                if (proteo != null) {
                    ProjectCard(navController, proteo)
                }
            }
            items(1) {
                AddProjectCard()
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCard(
    navController: NavController,
    token: Token
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(120.dp)
            .shadow(elevation = 5.dp)
            .padding(7.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        onClick = {
            navController.navigate(Screen.ProjectScreen.withArgs(token.identifier))
        }
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                modifier = Modifier.size(48.dp),
                painter = rememberAsyncImagePainter(model = token.pngUrl),
                contentDescription = token.name
            )
            Text(
                text = token.name,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun AddProjectCard() {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(120.dp)
            .shadow(elevation = 5.dp)
            .padding(7.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.ic_baseline_add_circle_outline_48),
                contentDescription = "Add a project"
            )
            Text(
                text = "Add",
                fontSize = 14.sp
            )
        }
    }
}