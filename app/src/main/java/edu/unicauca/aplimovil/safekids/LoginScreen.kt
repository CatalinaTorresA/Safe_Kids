package edu.unicauca.aplimovil.safekids


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.unicauca.aplimovil.safekids.ui.AppViewModelProvider
import edu.unicauca.aplimovil.safekids.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    onTeacherClick: (String)->Unit = {},
    onGuardianClick: (String)->Unit = {},
    onSaveClick: ()->Unit = {},
    onDescriptionClick: ()->Unit = {},
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    // Campos de entrada
    var cedula by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Menú desplegable
    var expanded by remember { mutableStateOf(false) }
    var selectedRol by remember { mutableStateOf("Acudiente") }

    var Docente: Boolean by remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6DDD6))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Título con sombra
            Text(
                text = "Colegio",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.logo), // tu recurso de logo
                contentDescription = "Logo del colegio",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color(0xFF122379))
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    // Título + Menú
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Log in",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Box {
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .clickable { expanded = true }
                                    .background(Color.Red)
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = selectedRol,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Expand",
                                    tint = Color(0xFF122379),
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(Color.Red)
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Acudiente", color = Color.White) },
                                    onClick = {
                                        selectedRol = "Acudiente"
                                        expanded = false
                                        Docente = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Docente", color = Color.White) },
                                    onClick = {
                                        selectedRol = "Docente"
                                        expanded = false
                                        Docente = true
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = viewModel.loginUiState.details.id,
                        onValueChange = { newValue ->
                            viewModel.updateUiState(
                                viewModel.loginUiState.details.copy(id = newValue)
                            )
                        },
                        label = {
                            Text("Cedula", color = Color.Black, fontWeight = FontWeight.Bold)
                        },
                        shape = RoundedCornerShape(32.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFE6DDD6),
                            focusedContainerColor = Color(0xFFE6DDD6),
                            focusedTextColor = Color.Red,
                            unfocusedTextColor = Color.Red,
                            cursorColor = Color.Red
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = viewModel.loginUiState.details.password,
                        onValueChange = { newValue ->
                            viewModel.updateUiState(
                                viewModel.loginUiState.details.copy(password = newValue)
                            )
                        },
                        label = {
                            Text("Password", color = Color.Black, fontWeight = FontWeight.Bold)
                        },
                        shape = RoundedCornerShape(32.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFE6DDD6),
                            focusedContainerColor = Color(0xFFE6DDD6),
                            focusedTextColor = Color.Red,
                            unfocusedTextColor = Color.Red,
                            cursorColor = Color.Red
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                if (Docente) {
                                    val result = viewModel.attemptTeacherLogin()
                                    if (result != null) {
                                        onTeacherClick(result.teacher_id)
                                    } else {
                                        Log.d("check1a", "Login failed for Teacher")
                                    }
                                } else {
                                    val result = viewModel.attemptGuardianLogin()
                                    if (result != null) {
                                        onGuardianClick(result.guardian_id)
                                        Log.d("check1b", "Login failed for Guardian")
                                    }
                                }
                            }
                        }
                        ,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(text = "Login", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
            //Boton para ir a la pantalla de descripcion
            Button(
                onClick = onDescriptionClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF122379)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .width(250.dp)
                    .height(48.dp)
            ) {
                Text(text = "Description", color = Color.White)
            }
        }
    }
}

