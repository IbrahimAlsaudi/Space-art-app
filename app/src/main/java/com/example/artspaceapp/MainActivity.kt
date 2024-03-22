package com.example.artspaceapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var result by remember { mutableIntStateOf(1) }
    val currentImage = when (result) {
        1 -> R.drawable.starrynight_van_gogh
        2 -> R.drawable.burning_windmill_johan_christian_1856
        3 -> R.drawable.the_ninth_wave_ivan_aivazovsky
        4 -> R.drawable.the_hay_wain_john_constable1821
        5 -> R.drawable.the_scream_edvard_munch
        6 -> R.drawable.the_shipwreck_claude_joseph_1772
        7 -> R.drawable.wanderer_above_the_sea_caspar_david_1818
        else -> R.drawable.watching_the_breakers_winslow_homer_1891
    }

    val currentAuthor = when (result) {
        1 -> R.string.van_gogh
        2 -> R.string.johan_christian
        3 -> R.string.ivan_aivazovsky
        4 -> R.string.john_constable
        5 -> R.string.edvard_munch
        6 -> R.string.claude_joseph
        7 -> R.string.caspar_david
        else -> R.string.winslow_homer
    }

    val currentName = when (result) {
        1 -> R.string.starry_night
        2 -> R.string.burning_windmill
        3 -> R.string.ninth_wave
        4 -> R.string.the_hay_wain
        5 -> R.string.the_scream
        6 -> R.string.ship_wreck
        7 -> R.string.wanderer_above_the_sea
        else -> R.string.watching_the_breakers
    }

    val currentDate = when (result) {
        1 -> R.string.starry_night_date
        2 -> R.string.burning_windmill_date
        3 -> R.string.ninth_wave_date
        4 -> R.string.the_hay_wain_date
        5 -> R.string.the_scream_date
        6 -> R.string.ship_wreck_date
        7 -> R.string.wanderer_above_the_sea_date
        else -> R.string.watching_the_breakers_date
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.background(Color.LightGray)
    ) {
        ImageWithSurface(currentImage)
        Spacer(modifier = Modifier.height(60.dp))
        ImageDescription(
            imageName = currentName,
            authorName = currentAuthor,
            imageDate = currentDate
        )
//        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            MyButton(
                text = "Previous",
                onClick = {
                    if (result == 1) {
                        result = 9
                    } else {
                        --result
                    }
                })
            Spacer(modifier = Modifier.width(24.dp))
            MyButton(
                text = "Next",
                onClick = {
                    if (result == 9) {
                        result = 1
                    } else {
                        ++result
                    }
                })
        }
    }
}

@Composable
fun ImageWithSurface(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 48.dp,
        modifier = modifier
//            .background(Color.Red)
//            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = modifier
                .size(width = 350.dp, height = 400.dp)
                .border(width = 36.dp, color = Color.White),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ImageDescription(
    @StringRes imageName: Int,
    @StringRes authorName: Int,
    @StringRes imageDate: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
//            .background(Color.LightGray)
            .padding(12.dp)
    ) {
        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Light
                )
            ) {
                append(stringResource(id = imageName))
            }
            append("\n")
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(stringResource(id = authorName))
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Gray

                )
            ) {
                append(stringResource(id = imageDate))
            }
        }

        Text(text = annotatedString)
    }
}

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(width = 170.dp, height = 42.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceScreen()
    }
}