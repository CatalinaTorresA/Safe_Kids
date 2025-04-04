package edu.unicauca.aplimovil.safekids

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun ActividadScreen(
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    // Lista de estudiantes
    val estudiantes = listOf("Pepito Perez", "Juanito Lopez", "Maria Gonzalez")
    // Estado para el desplegable
    var expanded by remember { mutableStateOf(false) }
    var estudianteSeleccionado by remember { mutableStateOf("Nombre del estudiante") }

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
                    .background(Color(0xFF122379), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ACTIVIDAD",
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

            // Recuadro con el nombre del estudiante y la flecha (más claro)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFBDB9B9), shape = RoundedCornerShape(16.dp)) // color #bdb9b9
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = estudianteSeleccionado,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f) // Acomoda el texto
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,  // Flecha hacia abajo
                        contentDescription = "Dropdown",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color(0xFFF8F8F8))
                    .fillMaxWidth()
            ) {
                estudiantes.forEach { estudiante ->
                    DropdownMenuItem(
                        onClick = {
                            estudianteSeleccionado = estudiante
                            expanded = false
                        },
                        text = { Text(text = estudiante, color = Color.Black) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Lista de Actividades con Fechas y Horas
            Column(modifier = Modifier.fillMaxWidth()) {
                // Puedes añadir las actividades de ejemplo aquí
                listOf(
                    "21/03/2025 1:30 pm",
                    "21/03/2025 6:45 am",
                    "22/03/2025 10:00 am"
                ).forEach { actividad ->
                    val (date, time) = actividad.split(" ") // Separar fecha y hora

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF8D8782)) // fondo gris claro
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Fecha alineada a la izquierda
                            Text(
                                text = date,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            // Hora alineada a la derecha
                            Text(
                                text = time,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Empuja la barra inferior hacia abajo

            // BARRA INFERIOR
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





