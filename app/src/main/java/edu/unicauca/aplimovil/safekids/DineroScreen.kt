package edu.unicauca.aplimovil.safekids

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.unicauca.aplimovil.safekids.ui.AppViewModelProvider
import edu.unicauca.aplimovil.safekids.ui.viewmodel.GuardianMoneyProfileViewModel
import edu.unicauca.aplimovil.safekids.ui.viewmodel.MoneyUiState
import edu.unicauca.aplimovil.safekids.ui.viewmodel.StudentUiState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun DineroScreen(
    onProfileClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    viewModel: GuardianMoneyProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    var showDialog by remember { mutableStateOf(false) }
    var showBlockDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp)
            .padding(12.dp)
    ) {
        // Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFF6C6C6C), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Acudiente",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val students by viewModel.students.collectAsState()
        DineroDropdown(students, viewModel)

        Spacer(modifier = Modifier.height(12.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDFF6DD))
            ) {
                Icon(Icons.Default.AttachMoney, contentDescription = "Recargar", tint = Color(0xFF28A745))
                Spacer(Modifier.width(4.dp))
                Text("Recargar", color = Color(0xFF28A745))
            }

            Button(
                onClick = { showBlockDialog = true},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE0E0))
            ) {
                Icon(Icons.Default.Block, contentDescription = "Bloquear", tint = Color(0xFFDC3545))
                Spacer(Modifier.width(4.dp))
                Text("Bloquear", color = Color(0xFFDC3545))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gastos
        Text(
            text = "Gastos",
            color = Color(0xFF202B7F),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE), RoundedCornerShape(20.dp))
                .padding(vertical = 6.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        val moneyList by viewModel.moneyList.collectAsState()
        MoneyList(moneyList)

        Spacer(modifier = Modifier.weight(1f))

        // Barra inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF8D8782), shape = RoundedCornerShape(12.dp))
                .padding(vertical = 12.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onHomeClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Red)
                    Text("Home", color = Color.Red)
                }
            }

            Button(
                onClick = onProfileClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                modifier = Modifier.weight(1f)
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

    // 🟢 Diálogo para recargar con método PSE
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {},
            dismissButton = {},
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = null,
                        tint = Color(0xFFB9EACB),
                        modifier = Modifier.size(80.dp)
                    )
                }
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Por favor, seleccione el método de pago",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "¡Y deja que tu hijo disfrute de tu dinero!",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Image(
                        painter = painterResource(id = R.drawable.logo_pse),
                        contentDescription = "PSE",
                        modifier = Modifier
                            .size(70.dp)
                            .clickable {
                                // Acción al seleccionar PSE
                                showDialog = false
                            }
                    )
                }
            },
            containerColor = Color(0xFFFDF4EF),
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }

    if (showBlockDialog) {
        AlertDialog(
            onDismissRequest = { showBlockDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Acción de confirmación de bloqueo
                        showBlockDialog = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Text("Estoy seguro", color = Color.Black)
                }
            },
            dismissButton = {},
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Block,
                        contentDescription = null,
                        tint = Color(0xFFF3B8B8),
                        modifier = Modifier.size(100.dp)
                    )
                }
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "¿Está seguro de bloquear\nla cartera?",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Su hijo/a no podrá hacer\nuso del dinero en el colegio",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            },
            containerColor = Color(0xFFFDF4EF),
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }

}

@Composable
fun DineroDropdown(students: List<StudentUiState>, viewModel: GuardianMoneyProfileViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var selectedOptionId by remember { mutableStateOf<String?>(null) }

    // Se declara un modificador para que el DropdownMenu tenga el mismo ancho que el Text
    val dropdownModifier = Modifier
        .fillMaxWidth() // Esto asegura que el DropdownMenu tenga el mismo ancho que el texto
        .background(Color.White)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {
        // Aquí la Text de "DINERO" con el mismo ancho del contenedor
        Text(
            text = selectedOption ?: "Seleccione un Estudiante",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF202B7F), RoundedCornerShape(12.dp))
                .clickable { expanded = true }
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )

        // DropdownMenu con el mismo ancho que la Text
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = dropdownModifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            students.forEach { student ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOption = student.name
                            expanded = false
                            selectedOptionId = student.student_id
                            coroutineScope.launch {
                                viewModel.updateStudentId(student.student_id)
                            }

                        }
                        .padding(8.dp)
                ) {
                    Text(text = student.name, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Composable
fun MoneyList(moneyList: List<MoneyUiState>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(495.dp)
            .background(Color(0xFFE0DCDC), RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        items(moneyList) { money ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(vertical = 4.dp)
                    .background(Color(0xFFB8B0AB), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp), // Para darle padding interno
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Money ID
                    Text(
                        text = money.transactionId.toString(),
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )

                    // Monto
                    Text(
                        text = if (money.amount >= 0) "+$${money.amount}" else "-$${-money.amount}",
                        color = if (money.amount >= 0) Color.Green else Color.Red,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    // Timestamp (alineado a la derecha)
                    Text(
                        text = money.transactionDate,
                        color = Color.White,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

