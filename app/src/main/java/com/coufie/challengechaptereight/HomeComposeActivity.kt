package com.coufie.challengechaptereight

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coufie.challengechaptereight.ui.theme.ChallengeChapterEightTheme

class HomeComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapterEightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting5("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting5(name: String) {

    val mContext = LocalContext.current
    Column(modifier = Modifier.padding(15.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_person_24),
            contentDescription = "icon",
            modifier = Modifier.height(25.dp)
                    .clickable { mContext.startActivity(Intent(mContext, ProfileComposeActivity::class.java)) }
        )

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview5() {
    ChallengeChapterEightTheme {
        Greeting5("Android")
    }
}