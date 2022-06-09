

package com.coufie.challengechaptereight

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.coufie.challengechaptereight.data.GetFilmItem
import com.coufie.challengechaptereight.ui.theme.ChallengeChapterEightTheme
import com.coufie.challengechaptereight.viewmodel.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeComposeActivity : ComponentActivity() {
    private lateinit var prefsLogin : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapterEightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val mcontext = LocalContext.current
                    val filmViewModel = viewModel(modelClass = FilmViewModel::class.java)
                    val dataFilm by filmViewModel.dataState.collectAsState()
                    prefsLogin = mcontext.getSharedPreferences("SF", Context.MODE_PRIVATE)

                    Column {

                        Dashboard(prefsLogin)

                        LazyColumn(modifier = Modifier) {

                            if (dataFilm.isEmpty()) {
                                item {

                                }
                            }

                            items(dataFilm) {
                                FilmUI(film = it)
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Dashboard(prefsLogin : SharedPreferences){
    val mcontext = LocalContext.current
    val name = prefsLogin.getString("NAME", "")

    Row(modifier = Modifier
        .height(50.dp)
        .padding(start = 15.dp, end = 15.dp)
        .fillMaxWidth(),
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight()) {
                Text(
                    color = Color.Black,
                    text = name.toString()
                )
            }


            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd){

                Button(border = BorderStroke(width = 1.dp,color = Color.White),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.White),
                    onClick = {
                        prefsLogin.edit().clear().apply()
                        Toast.makeText(mcontext, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                        mcontext.startActivity(Intent(mcontext, LoginComposeActivity::class.java))

                    }) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(ButtonDefaults.IconSize
                        )
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = "Logout")
                }
            }


    }



}

@Composable
fun FilmUI(film : GetFilmItem) {
    val mcontext = LocalContext.current

    Column(modifier = Modifier
        .padding(15.dp)) {



        Card(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                val pindah = Intent(mcontext, FilmDetailCompose::class.java)
//                pindah.putExtra("image", film.image)
//                pindah.putExtra("title", film.name)
//                pindah.putExtra("director", film.director)
//                pindah.putExtra("date", film.date)
                pindah.putExtra("film", film)
                mcontext.startActivity(pindah)
            }
        ) {
            Row {
                Image(painter = rememberImagePainter(data = film.image ), contentDescription = "img", modifier = Modifier
                    .height(50.dp))
                Column(modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth()
                ) {
                    Text(text = "Title : ${film.name}")
                    Text(text = "Director : ${film.director}")
                    Text(text = "Date : ${film.date}")
                }

            }
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview5() {
    ChallengeChapterEightTheme {

    }
}