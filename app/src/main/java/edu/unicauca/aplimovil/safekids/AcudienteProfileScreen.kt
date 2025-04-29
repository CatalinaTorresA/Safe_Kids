package edu.unicauca.aplimovil.safekids

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.unicauca.aplimovil.safekids.ui.AppViewModelProvider
import edu.unicauca.aplimovil.safekids.ui.viewmodel.GuardianProfileViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.StudentUiState
import edu.unicauca.aplimovil.safekids.ui.components.BottomNavigationBar


@Composable
fun AcudienteProfileScreen(
    onProfileClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    viewModel: GuardianProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    // Variables para los campos de texto
    val tipoAcudiente = "Abuelo"
    var nombre by remember { mutableStateOf<String?>(null) }
    var cedula by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val teacher = viewModel.loadGuardian()
        teacher?.let {
            nombre = it.name
            cedula = it.id
        }
    }

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
                    fontSize = 28.sp, // Aumento el tamaño de la fuente
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
                    fontSize = 22.sp, // Aumento el tamaño de la fuente
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8D8782)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tipo de acudiente (estático)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE6DDD6))
                        .padding(16.dp)
                ) {
                    Text(text = "Tipo de acudiente: $tipoAcudiente", color = Color.Black, fontSize = 18.sp)
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

            // Estudiantes registrados
            Text(
                text = "Estudiantes registrados",
                fontSize = 22.sp, // Aumento el tamaño de la fuente
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8D8782)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val students by viewModel.students.collectAsState()
            StudentList(students)

            Spacer(modifier = Modifier.weight(1f)) // Empuja la barra inferior hacia abajo

            // BARRA INFERIOR
            BottomNavigationBar(onHomeClick = onHomeClick, onProfileClick = onProfileClick)
        }
    }
}

@Composable
fun StudentList(student: List<StudentUiState>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .padding(horizontal = 12.dp)
    ) {
        items(student) { student ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(Color(0xFF8D8782), shape = RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF122379))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = student.name,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Código: ${student.student_id}",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
