package com.coufie.challengechaptereight

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import com.coufie.challengechaptereight.data.PostUser
import com.coufie.challengechaptereight.ui.theme.ChallengeChapterEightTheme
import com.coufie.challengechaptereight.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    registerUI()
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun registerUI() {

    val userVM = viewModel(modelClass = UserViewModel::class.java)

//    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
//    var passwordcek by remember { mutableStateOf("") }


    // password text state
    var password by remember {
        mutableStateOf("")
    }
    var passwordcek by remember {
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
            text = "Register"
        )

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


        OutlinedTextField(label = { Text("password") },
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


        OutlinedTextField(label = { Text("passwordcek") },
            modifier = Modifier
                .fillMaxWidth()
                .width(350.dp)
                .padding(start = 10.dp, end = 10.dp),

            value = passwordcek,
            onValueChange = {
                passwordcek = it
            },
            placeholder = {
                Text(text = "Password Cek")
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
                PasswordVisualTransformation())


        Spacer(
            modifier = Modifier.padding(25.dp)
        )

        Button(colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black),
            modifier = Modifier.width(150.dp).height(50.dp),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(width = 1.dp,color = Color.Blue),onClick = {
            if(password != passwordcek){
                Toast.makeText(mcontext, "Password tidak sama", Toast.LENGTH_SHORT).show()
            }else if(username.isEmpty() || password.isEmpty() || passwordcek.isEmpty()){
                Toast.makeText(mcontext, "Mohon isi data dengan lengkap", Toast.LENGTH_SHORT).show()
            }else{
                userVM.registerUser(
                    PostUser(
                        username,
                        password,
                        "jakarta",
                        "https://loremflickr.com/640/480",
                        "anonymous",
                        21
                        )
                )
                mcontext.startActivity(Intent(mcontext, LoginComposeActivity::class.java))
            }
        }) {
            Text(text = "Register")
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
                mcontext.startActivity(Intent(mcontext, LoginComposeActivity::class.java))

            }) {
            Text(text = "Back")
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    ChallengeChapterEightTheme {
    }
}