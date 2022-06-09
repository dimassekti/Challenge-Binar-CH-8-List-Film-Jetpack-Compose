@file:Suppress("SimplifyBooleanWithConstants", "UnusedImport", "UnusedImport", "UnusedImport")

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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coufie.challengechaptereight.ui.theme.ChallengeChapterEightTheme
import com.coufie.challengechaptereight.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginComposeActivity : ComponentActivity() {
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

                    prefsLogin = mcontext.getSharedPreferences("SF", Context.MODE_PRIVATE)
                    if(mcontext.getSharedPreferences("SF", Context.MODE_PRIVATE).contains("USERNAME") && mcontext.getSharedPreferences("SF", Context.MODE_PRIVATE).contains("PASSWORD")){
                        mcontext.startActivity(Intent(mcontext, HomeComposeActivity::class.java))
                    }

                    LoginUI(prefsLogin)
                }
            }
        }
    }
}

@Composable
fun LoginUI(prefsLogin : SharedPreferences) {

    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
    val userVM = viewModel(modelClass = UserViewModel::class.java)
    val dataUser by userVM.dataUserState.collectAsState()

    // password text state
    var password by remember {
        mutableStateOf("")
    }

    //password visibility state
    var isVisible by remember {
        mutableStateOf(false)
    }

    val icon = if (isVisible)
        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
    else
        painterResource(id = R.drawable.ic_baseline_visibility_off_24)

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
            painter = painterResource(R.drawable.a20may2022),
            contentDescription = "icon")

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        val mcontext = LocalContext.current


        OutlinedTextField(

            modifier = Modifier
            .fillMaxWidth()
            .width(350.dp)
            .padding(start = 10.dp, end = 10.dp),
            value = username, onValueChange = {username = it},
            label = { Text("username") })

        OutlinedTextField(
            label = { Text("password") },
            modifier = Modifier
                .fillMaxWidth()
                .width(350.dp)
                .padding(start = 10.dp, end = 10.dp),

            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = "Password")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        isVisible = !isVisible
                    }) {
                    Icon(painter = icon, contentDescription = "null")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation =
            if (isVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation()
        )

        Spacer(
            modifier = Modifier.padding(25.dp)
        )

        Button(colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black),
            modifier = Modifier.width(150.dp).height(50.dp),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(width = 1.dp,color = Color.Blue),
            onClick = {
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(mcontext, "Mohon isi data dengan lengkap", Toast.LENGTH_SHORT).show()
            }else{
                var cek = false
                for (i in dataUser.indices ){
                    if (username == dataUser[i].username && password == dataUser[i].password) {
                        prefsLogin.edit().putString("PASSWORD", dataUser[i].password).apply()
                        prefsLogin.edit().putString("USERNAME", dataUser[i].username).apply()
                        prefsLogin.edit().putString("NAME", dataUser[i].name).apply()
                        cek = true
                        Toast.makeText(mcontext, "Login berhasil", Toast.LENGTH_SHORT).show()
                        mcontext.startActivity(Intent(mcontext, HomeComposeActivity::class.java))
                    }
                }
                if(cek == false){
                    Toast.makeText(mcontext, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text(text = "Login")
        }

        Spacer(
            modifier = Modifier.padding(5.dp)
        )

        Button(colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black),
            modifier = Modifier.width(150.dp).height(50.dp),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(width = 1.dp,color = Color.Blue),onClick = {
            mcontext.startActivity(Intent(mcontext, RegisterComposeActivity::class.java))

        }) {
            Text(text = "Register")
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    ChallengeChapterEightTheme {
    }
}