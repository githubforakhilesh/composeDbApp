package com.example.newdbapp.Presenter.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newdbapp.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(modifier: Modifier = Modifier,onSubmitClick:()-> Unit) {
    var userName by remember { mutableStateOf("") } // Assuming you have this state defined

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Sign Up",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Normal
                        )
                     }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )

            )
        },
    ) { innerPadding -> // 2. This padding MUST be used

        Column(
            modifier = Modifier             // 3. FIX: Capitalized 'M'
                .padding(innerPadding)      // 4. FIX: Applied innerPadding so text isn't hidden
                .fillMaxSize(),             // Replaced fillMaxHeight with fillMaxSize for centering
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = userName,
                onValueChange = { newText -> userName = newText },
                placeholder = { Text(text = "Enter UserName") },
                shape = RoundedCornerShape(10.dp), // This rounds the border corners
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,   // Border color when typing
                    unfocusedBorderColor = Color.Green,  // Border color when idle
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Button(
                onClick = {

                },
                modifier = Modifier.padding(15.dp).wrapContentWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Used standard primary
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                // 6. FIX: Changed lowercase 'modifier' to uppercase 'Modifier'
                Text("Submit", modifier = Modifier.padding(4.dp))
            }
        }
    }


}
