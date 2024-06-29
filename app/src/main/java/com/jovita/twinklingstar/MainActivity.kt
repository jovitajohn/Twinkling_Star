package com.jovita.twinklingstar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jovita.twinklingstar.ui.theme.TwinklingStarTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwinklingStarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TwinklingStarBase()
                }
            }
        }
    }
}

@Composable
fun TwinklingStarBase(modifier: Modifier = Modifier) {
    Box {


        var showStars by remember { mutableStateOf(false) } // Track if stars are shown
        var showStars2 by remember { mutableStateOf(false) } // Track if stars are shown
        var showStars3 by remember { mutableStateOf(false) } // Track if stars are shown

        LaunchedEffect(Unit) {
            repeat(20) {
                delay(500)
                showStars = true // Set showStars to true after each delay
                delay(500) // Optional delay for star visibility (adjust as needed)
                showStars = false // Hide star after additional delay
            }
        }
        LaunchedEffect(Unit) {
            repeat(20) {
                delay(700)
                showStars2 = true // Set showStars to true after each delay
                delay(700) // Optional delay for star visibility (adjust as needed)
                showStars2 = false // Hide star after additional delay
            }
        }
        LaunchedEffect(Unit) {
            repeat(20) {
                delay(1100)
                showStars3 = true // Set showStars to true after each delay
                delay(1100) // Optional delay for star visibility (adjust as needed)
                showStars3 = false // Hide star after additional delay
            }
        }

        if (showStars) {
            AddStar() // Call AddStar conditionally
        }
        if (showStars2) {
            AddStar() // Call AddStar conditionally
        }
        if (showStars3) {
            AddStar() // Call AddStar conditionally
        }
    }

}


@Composable
fun AddStar() {

    var showView by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val heightRange =
        100..screenHeightDp.value.toInt() - 100 // Range from 0 (inclusive) to screen height (inclusive)
    val randHeight = heightRange.random()

    val widthRange =
        100..screenWidthDp.value.toInt() - 100 // Range from 0 (inclusive) to screen width (inclusive)
    val randWidth = widthRange.random()

    LaunchedEffect(Unit) { // LaunchedEffect for side effects
        delay(10000) // delay for 10 seconds (1000 milliseconds)
        showView = false
    }

    if (showView) {
        AnimatedPreloader(
            modifier = Modifier
                .size(200.dp)
                .offset(x = randWidth.dp, y = randHeight.dp)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    TwinklingStarTheme {
        TwinklingStarBase()
    }
}

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.star
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}