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

class ProfileComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapterEightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting4("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting4(name: String) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(15.dp)
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Dimas Sekti Adji"
        )

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        Image(
            painter = painterResource(R.drawable.jett),
            contentDescription = "icon")

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        val mContext = LocalContext.current
        Button(onClick = {
            mContext.startActivity(Intent(mContext, LoginComposeActivity::class.java))
        }, colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Text(text = "Logout")
        }

        Button(onClick = {
            mContext.startActivity(Intent(mContext, HomeComposeActivity::class.java))
        }, colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    ChallengeChapterEightTheme {
        Greeting4("Android")
    }
}