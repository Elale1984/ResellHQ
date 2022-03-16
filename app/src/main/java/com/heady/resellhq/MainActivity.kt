package com.heady.resellhq

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.heady.resellhq.ui.theme.*

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
            
        }
        setContent {
            ResellHQTheme {
                LoginScreen(auth)
            }
        }
    }
}

@Composable
fun LoginScreen(auth: FirebaseAuth){
    Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.BackgroundColor){
        Column (modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            WelcomeText()
            PurposeImage()
            Text_field(auth, InputType = KeyboardType.Email,"E-mail address",IconImage = painterResource(id = R.drawable.ic_email))
            ForgotPasswordText()
        }
    }
}
@Composable
fun WelcomeText(){
    Text(text = "Welcome",
        color = Color.White,
        fontSize = 25.sp,
        modifier = Modifier.padding(top = 40.dp)
    )
}
@Composable
fun PurposeImage(){
    Image(painter = painterResource(id = R.drawable.resellhq_logo), contentDescription = "LocationPin",
        modifier = Modifier.size(300.dp))
}
@Composable
fun Text_field(auth: FirebaseAuth, InputType : KeyboardType, placeholder : String, IconImage : Painter){
    val textFieldEmailState = remember{mutableStateOf("")}

    TextField(value = textFieldEmailState.value,
        onValueChange = { newInput -> textFieldEmailState.value = newInput },
        leadingIcon = {Image(painter = painterResource(id = R.drawable.ic_email), contentDescription = "email")},
        label = {Text(text = "E-mail address",color = MaterialTheme.colors.TextFieldTextColor)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.TextFieldColor)
    )
    val textFieldPasswordState = remember{mutableStateOf("")}
    TextField(value = textFieldPasswordState.value,
        onValueChange = { newInput -> textFieldPasswordState.value = newInput },
        leadingIcon = {Image(painter = painterResource(id = R.drawable.ic_password), contentDescription = "password")},
        label = {Text(text = "Password",color = MaterialTheme.colors.TextFieldTextColor)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.TextFieldColor)
    )
    val context = LocalContext.current
    Button(onClick = {
        auth.signInWithEmailAndPassword(textFieldEmailState.value, textFieldPasswordState.value)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Login was successful",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {

                    Toast.makeText(
                        context,
                        "Login was not successful try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    },modifier = Modifier
        .padding(top = 25.dp)
        .requiredWidth(277.dp)){
        Text(text = "Sign In")
    }
}

@Composable
fun ForgotPasswordText(){
    Text(text = "Forgot Password ?",color = MaterialTheme.colors.TextFieldTextColor,
        modifier = Modifier.padding(top = 70.dp))
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ResellHQTheme {
        LoginScreen(Firebase.auth)
    }
}