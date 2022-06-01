package com.coufie.challengechaptereight

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coufie.challengechaptereight.ui.theme.ChallengeChapterEightTheme

class RegisterComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapterEightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var passwordcek by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(15.dp)
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Register"
        )

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        val mcontext = LocalContext.current
        TextField(value = email, onValueChange = {email = it},
            label = { Text("email") })
        TextField(value = username, onValueChange = {username = it},
            label = { Text("username") })
        TextField(value = password, onValueChange = {password = it},
            label = { Text("password") })
        TextField(value = passwordcek, onValueChange = {passwordcek = it},
            label = { Text("passwordcek") })
        Button(onClick = {
            mcontext.startActivity(Intent(mcontext, LoginComposeActivity::class.java))
        }) {
            Text(text = "Register")
        }
        Text(text = "sudah punya account?")

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    ChallengeChapterEightTheme {
        Greeting3("Android")
    }
}