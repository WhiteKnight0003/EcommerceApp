package com.example.ecommerceapp.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
    onSignUpSucess: () -> Unit
) {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var confirmPassword = remember { mutableStateOf("") }
    var passwordError = remember { mutableStateOf<String?>(null) }
    val authState = true

    // Sử dụng LaunchedEffect để xử lý điều hướng sau khi trạng thái thay đổi
    if (authState) {
        LaunchedEffect(Unit) {
            onSignUpSucess()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Create Accouunt",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value= email.value,
            onValueChange = {email.value = it},
            label = {Text("Email")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            singleLine = true
        )
        OutlinedTextField(
            value= password.value,
            onValueChange = {password.value = it},
            label = {Text("Password")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            singleLine = true
        )

        OutlinedTextField(
            value= confirmPassword.value,
            onValueChange = {confirmPassword.value = it},
            label = {Text("Comfirm Password")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            singleLine = true,
            isError = passwordError.value != null,
            supportingText = {
                passwordError.value?.let{
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if(password.value != confirmPassword.value){
                    passwordError.value = "Password do not match"
                }else if(password.value.length <6){
                    passwordError.value = "Password should be at least 6 character"
                }
                else{
                    passwordError.value = null
                    // View Model call
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
//            enabled = email.value.isNotEmpty() && password.value.isNotEmpty() && confirmPassword.value.isNotEmpty() && authState,
//            !
        ){
            Text("Sign Up")

            Spacer(Modifier.height(16.dp))

            TextButton(onClick = onNavigateToLogin) {
                Text("Already have an account? Login")
            }
        }
    }
}