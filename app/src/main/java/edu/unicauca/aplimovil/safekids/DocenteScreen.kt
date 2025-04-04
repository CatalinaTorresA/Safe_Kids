package edu.unicauca.aplimovil.safekids

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DocenteScreen(
    onProfileClick: ()->Unit = {},
    onHomeClick: ()->Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6DDD6)) // fondo beige claro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // ENCABEZADO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .background(Color(0xFF8D8782), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Docente",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // BOTÓN ACTIVIDAD
                Button(
                    onClick = { /* Acción para Actividad */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF122379))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Actividad",
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                // BOTÓN AGENDA
                Button(
                    onClick = { /* Acción para Agenda */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF122379))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Agenda",
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }















            Spacer(modifier = Modifier.weight(1f)) // Empuja la barra inferior hacia abajo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF8D8782), shape = RoundedCornerShape(12.dp))
                    .padding(vertical = 12.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón Home
                Button(
                    onClick = onHomeClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Red)
                        Text("Home", color = Color.Red)
                    }
                }

                    // Botón Profile
                    Button(
                        onClick = onProfileClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.perfil),
                                contentDescription = "Profile Pic",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                text = "Profile",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }
            }
        }
    }