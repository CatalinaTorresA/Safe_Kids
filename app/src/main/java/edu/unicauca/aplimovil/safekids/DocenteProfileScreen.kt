package edu.unicauca.aplimovil.safekids

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration


@Composable
fun DocenteProfileScreen(
    onProfileClick: () -> Unit = {},
    onHomeClick: () -> Unit = {}
) {
    // Variables para los campos de texto
    val tipoDocente = "Profesor"
    val nombre = "Pepito Alvarez"
    val cedula = "1234"

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
                    painter = painterResource(id = R.drawable.logo), // tu logo
                    contentDescription = "Logo",
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección de Información
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Información",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8D8782)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tipo de docente (estático)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE6DDD6))
                        .padding(16.dp)
                ) {
                    Text(text = "Tipo de docente: $tipoDocente", color = Color.Black, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nombre (estático)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE6DDD6))
                        .padding(16.dp)
                ) {
                    Text(text = "Nombre: $nombre", color = Color.Black, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Cédula (estático)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE6DDD6))
                        .padding(16.dp)
                ) {
                    Text(text = "Cédula: $cedula", color = Color.Black, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Cursos registrados
            Text(
                text = "Cursos registrados",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8D8782)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de cursos
            Column {
                val courses = listOf("Algebra", "Biologia", "Etica")
                val studentCount = listOf(4, 9, 11)

                courses.forEachIndexed { index, course ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .background(Color(0xFF8D8782), shape = RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        // Cuadro de color
                        Box(
                            modifier = Modifier
                                .size(60.dp) // Aumentamos el tamaño
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF122379)) // azul oscuro
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = course,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp // Aumento el tamaño de la fuente
                            )
                            Text(
                                text = "${studentCount[index]} Estudiantes",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
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
