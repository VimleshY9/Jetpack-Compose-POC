package com.vimlesh.composepoc1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}
@Composable
fun myApp(modifier: Modifier = Modifier, list:List<String> = listOf("world", "compose")){
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
Column(modifier = Modifier.padding(vertical = 4.dp)) {
    for (name in list) {
        Greeting(name)
    }
}
    }
}

@Composable
fun Greeting(name: String) {
    var expanded = remember {
        mutableStateOf(false)
    }
    var extraPadding = if(expanded.value) 48.dp else 0.dp
    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(
        vertical = 4.dp, horizontal = 8.dp
    )) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                Modifier
                    .weight(1f).padding(bottom = extraPadding)) {
                Text(text = "Hello" )
                Text(text = "$name!" )
            }
            ElevatedButton(onClick =  { expanded.value = !expanded.value}) {
                Text(if(expanded.value)"Show More" else "show less")
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