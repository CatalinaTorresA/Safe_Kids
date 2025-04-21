package edu.unicauca.aplimovil.safekids

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DescriptionScreen() {
    val backgroundColor = Color(0xFFF0E8E3)
    val cardColor = Color(0xFF1A237E)
    val redColor = Color(0xFFE53935)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Colegio",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .background(cardColor, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.logo), // Usa tu logo aquí
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "¿Qué es esta aplicación?",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Esta aplicación está diseñada para mejorar la seguridad de los niños mediante el control de entrada y salida, la gestión de agendas escolares, y un sistema propio de billetera institucional.",
                    color = Color.White,
                    fontSize = 16.sp
                )

                Divider(color = Color.White.copy(alpha = 0.5f))

                Text(
                    text = "Créditos:",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "• Miguel Ángel Maldonado Bautista\n• Bryan Andrés Suárez Sánchez\n• Catalina Torres Arenas",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}
