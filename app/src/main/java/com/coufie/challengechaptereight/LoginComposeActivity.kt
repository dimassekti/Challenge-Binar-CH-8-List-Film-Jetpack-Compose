package com.coufie.challengechaptereight

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coufie.challengechaptereight.ui.theme.ChallengeChapterEightTheme

class LoginComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapterEightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(15.dp)
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login"
        )

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "icon")

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        val mcontext = LocalContext.current
        TextField(value = email, onValueChange = {email = it},
            label = { Text("email") })
        TextField(value = password, onValueChange = {password = it},
            label = { Text("password") })

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        Button(onClick = {
            mcontext.startActivity(Intent(mcontext, HomeComposeActivity::class.java))
        }) {
            Text(text = "Login")
        }
        Button(onClick = {
            mcontext.startActivity(Intent(mcontext, RegisterComposeActivity::class.java))
        }, colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Text(text = "belum punya accout?")
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    ChallengeChapterEightTheme {
        Greeting2("Android")
    }
}