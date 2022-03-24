package com.heady.resellhq

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heady.resellhq.ui.theme.BackgroundColor


@Preview(showBackground = true)
@Composable
fun SignUp (
){
    Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.BackgroundColor) {
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            
            WelcomeNewUserText()
            LogoName()

        }
    }
}


@Composable
fun WelcomeNewUserText() {
    Text( text = "WELCOME TO",
        color = Color.White,
        fontSize = 50.sp,
        modifier = Modifier.padding(top = 60.dp),
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun LogoName() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(), content = {
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.resellhq_logo),
                contentDescription = "Logo",
                modifier = Modifier.clip(RoundedCornerShape(10)),
            )
        }
    )


}