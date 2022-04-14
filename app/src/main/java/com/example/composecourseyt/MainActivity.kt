package com.example.composecourseyt

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color(0xff202020), modifier = Modifier.fillMaxSize()) {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "MAIN SCREEN",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = {
                    OvershootInterpolator(6f).getInterpolation(it)
                }
            )
        )
        delay(300L)
        navController.navigate("main_screen")
    }
    Box(
        contentAlignment = Alignment.Center, 
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value),

        )
    }
}