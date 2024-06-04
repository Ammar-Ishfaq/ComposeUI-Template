package com.m.ammar.composeTemplate.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.m.ammar.composeTemplate.R
import com.m.ammar.composeTemplate.models.ResistorObject
import com.m.ammar.composeTemplate.navigation.TopLevelDestination
import com.m.ammar.composeTemplate.ui.components.ErrorItem
import com.m.ammar.composeTemplate.ui.components.AppLoader
import com.m.ammar.composeTemplate.ui.components.VerticalSpacer
import com.m.ammar.composeTemplate.ui.dialog.PermissionBox
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    loadData: () -> Unit,
    onNavigateClick: (source: String) -> Unit
) {
    LaunchedEffect(Unit) {
        loadData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {

                is HomeScreenUiState.Loading -> {
                    AppLoader(modifier = Modifier.fillMaxSize())
                }

                is HomeScreenUiState.Success -> {
                    HomeScreenContent(
                        modifier = Modifier.fillMaxSize(),
                        msg = uiState.obj.name,
                        imgURL = uiState.obj.image,
                        onNavigateClick = onNavigateClick
                    )
                }

                is HomeScreenUiState.Error -> {
                    ErrorItem(
                        text = uiState.msg,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                HomeScreenUiState.Initial -> {}

            }
        }
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    msg: String,
    imgURL: String,
    onNavigateClick: (source: String) -> Unit
) {
    val context = LocalContext.current
    var isAskLocationPermission by remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Create an ImagePainter with Coil
            KamelImage(
                resource = asyncPainterResource(data = imgURL),
                contentDescription = imgURL,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.LightGray),
            )
            Text(text = msg)
            VerticalSpacer(size = 16)
            Button(
                onClick = {
                    onNavigateClick(
                        context.getString(R.string.screen_name)
                            .format(TopLevelDestination.Home.title)
                    )
                }
            ) {
                Text(
                    text = stringResource(
                        R.string.go_to_screen,
                        TopLevelDestination.Detail.title
                    )
                )
            }
            VerticalSpacer(size = 16)

            Button(
                onClick = {
                    isAskLocationPermission = true
                }
            ) {

                Text(
                    text = stringResource(
                        R.string.permission_test
                    )
                )
            }
        }

        if (isAskLocationPermission) {
            PermissionBox(
                permissions = listOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                onBackPressed = {
                    isAskLocationPermission = false
                }
            ) {
                // you can wrap any widget down there that require above list of permissions
                Text(
                    text = stringResource(
                        R.string.permission_granted
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        loadData = {},
        uiState = HomeScreenUiState.Success(
            obj = ResistorObject(
                1,
                "ComposeUI",
                "imgUrl",
                "just a testing description"
            )
        ),
        onNavigateClick = {}
    )
}
