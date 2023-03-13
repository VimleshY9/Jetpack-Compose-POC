package com.vimlesh.composepoc1

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vimlesh.composepoc1.ui.theme.ComposePOC1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePOC1Theme {
                // A surface container using the 'background' color from the theme
                myApp(modifier = Modifier.fillMaxSize())

            }
        }
    }
}
@Composable
fun myApp(modifier: Modifier = Modifier){
    var shouldShowOnboarding by rememberSaveable() {
        mutableStateOf(true)
    }
    Surface(modifier) {
        if(shouldShowOnboarding){
            onBoardingScreen(onContinueCliked = {shouldShowOnboarding = false})
        }
        else{
            Greetings()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(if(expanded) 48.dp else 0.dp, animationSpec = SpringSpec(
        dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
    )
    )

    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(
        vertical = 4.dp, horizontal = 8.dp
    )) {
        Row( modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "Hello" )
                Text(text = "$name!", style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                if (expanded) {
                    Text(
                        text = ("This is simple compose tutorial" +
                                "Compose is such a wonderful UI toolkit ").repeat(4),
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
            }

        }

    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePOC1Theme {
        myApp()
    }
}

@Composable
fun onBoardingScreen(onContinueCliked: ()->Unit ,modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome to the Compose")
        IconButton(modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueCliked) {
          Text(text = "Home")
        }
    }
}
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview(){
    ComposePOC1Theme {
        onBoardingScreen(onContinueCliked = {})
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier, names : List<String> = List(1000){"$it"}){
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
           items(items = names){
               name ->
               Greeting(name = name)
           }
    }
}

//@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES,
//name = "Dark")
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview(){
    ComposePOC1Theme() {
        Greetings()
    }
}

@Composable
fun MyAppPreview(){
    ComposePOC1Theme {
        myApp(Modifier.fillMaxSize())
    }
}