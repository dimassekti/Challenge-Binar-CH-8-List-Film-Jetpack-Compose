@file:Suppress("RemoveSingleExpressionStringTemplate", "RemoveSingleExpressionStringTemplate",
    "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport",
    "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport",
    "UnusedImport", "UnusedImport", "UnusedImport", "CanBeVal", "CanBeVal", "CanBeVal", "CanBeVal",
    "CanBeVal", "CanBeVal", "CanBeVal"
)

package com.coufie.challengechaptereight

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.coufie.challengechaptereight.data.GetFilmItem
import com.coufie.challengechaptereight.ui.theme.ChallengeChapterEightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapterEightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    val image = intent.getStringExtra("image")
                    var dataFilmSerializable = intent.getSerializableExtra("film") as GetFilmItem?
                    var title = dataFilmSerializable!!.name
                    var date = dataFilmSerializable.date
                    var director = dataFilmSerializable.director
                    var image = dataFilmSerializable.image
                    var description = dataFilmSerializable.description
                    detailUI(title , date , director , image ,description)
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun detailUI(title:String, date:String, director:String, image:String, description:String) {
    val mcontext = LocalContext.current

    Column(modifier = Modifier
        .padding(15.dp)) {
        Card(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight()

        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = rememberImagePainter(data = image ), contentDescription = null)
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                    .padding(start = 20.dp)
                ) {
                    Text(text = "$title", fontSize = 24.sp)
                    Text(text = "$director")
                    Text(text = "Date : $date")

                    Spacer(
                        modifier = Modifier.padding(15.dp)
                    )
                    Text(text = "Description : $description")

                    Spacer(
                        modifier = Modifier.padding(15.dp)
                    )

                    Button(onClick = {
                        mcontext.startActivity(Intent(mcontext, HomeComposeActivity::class.java))

                    }, colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                        Text(text = "Home")
                    }
                }
            }

        }



    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview6() {
    ChallengeChapterEightTheme {
    }
}