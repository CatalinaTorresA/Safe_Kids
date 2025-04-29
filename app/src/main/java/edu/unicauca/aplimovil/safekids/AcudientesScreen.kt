package edu.unicauca.aplimovil.safekids

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
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
import edu.unicauca.aplimovil.safekids.ui.components.BottomNavigationBar

@Composable
fun AcudientesScreen(
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onActividadClick: ()->Unit = {},
    onDineroClick: ()->Unit = {}

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
                    text = "Acudiente",
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

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // ACTIVIDAD
                Button(
                    onClick = onActividadClick,
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

                // DINERO
                Button(
                    onClick = onDineroClick,
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
                            text = "Dinero",
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                // AGENDA
                Button(
                    onClick = {
                        // Acción para "Agenda"
                    },
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



            Spacer(modifier = Modifier.height(24.dp))

            // BARRA INFERIOR
            Spacer(modifier = Modifier.weight(1f)) // Empuja la barra de saldo hacia abajo

            // SALDO
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF122379))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = "Saldo",
                        tint = Color.Red,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = "Saldo", color = Color.White)
                        Text(text = "$ 0", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp)) // Un pequeño espacio antes de la barra inferior

            // BARRA INFERIOR
            BottomNavigationBar(onHomeClick = onHomeClick, onProfileClick = onProfileClick)
        }
    }
}
