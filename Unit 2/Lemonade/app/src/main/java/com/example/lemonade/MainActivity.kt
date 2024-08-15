package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeApp(
                Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var step by remember {
        mutableIntStateOf(0)
    }
    val imagePainter = when(step) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val instruction = when(step) {
        0 -> R.string.lemon_tree_instruction
        1 -> R.string.lemon_instruction
        2 -> R.string.lemonade_glass_instruction
        else -> R.string.empty_glass_instruction
    }
    val lowerLimits = intArrayOf(1, 2, 1, 1)
    val upperLimits = intArrayOf(1, 4, 1, 1)
    var tapLimit: Int by remember {
        mutableIntStateOf((lowerLimits[step]..upperLimits[step]).random())
    }
    var tapCount by remember{
        mutableIntStateOf(0)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                tapCount++
                if(tapCount >= tapLimit) {
                    step = (step + 1) % lowerLimits.size
                    tapLimit = (lowerLimits[step]..upperLimits[step]).random()
                    tapCount = 0
                }
            }
        ) {
            Image(
                painter = painterResource(imagePainter),
                contentDescription = null
            )
        }
        Spacer(
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(instruction),
            fontSize = 16.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeApp(
        Modifier
            .fillMaxSize()
    )
}