package ies.murgi.tev3_faris
//cambia el proyecto
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Principal()
            }
        }
    }
}

@Composable
fun Principal() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Superior()
            MiSlider()
            BarraProgreso()
        }
    }
}

@Preview
@Composable
fun MiSlider() {
    val range = 0f..50f
    var fontSize by remember { mutableStateOf(25f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.height(100.dp)) {
            Text(text = "Hola mundo", fontSize = fontSize.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = fontSize,
                valueRange = range,
                onValueChange = { fontSize = it },
                modifier = Modifier
                    .weight(0.9f)
                    .padding(end = 16.dp)
            )
            Text(
                text = fontSize.toInt().toString(),
                modifier = Modifier.weight(0.1f)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Superior() {
    var mostrarMenu by remember { mutableStateOf(false) }
    var mostrarSubMenu by remember { mutableStateOf<String?>(null) }
    var contadorNotificaciones by remember { mutableStateOf(0) }

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Faris",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            navigationIcon = {
                Box {
                    IconButton(onClick = { mostrarMenu = true }) {
                        Icon(Icons.Default.Menu, "Menú")
                    }
                    DropdownMenu(
                        expanded = mostrarMenu,
                        onDismissRequest = {
                            mostrarMenu = false
                            mostrarSubMenu = null
                        }
                    ) {
                        DropdownMenuItem(
                            text = { Text("CF Grado Superior") },
                            onClick = { mostrarSubMenu = "superior" },
                            leadingIcon = { Icon(Icons.Default.School, null) },
                            trailingIcon = { Icon(Icons.Default.ArrowRight, null) }
                        )
                        if (mostrarSubMenu == "superior") {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { mostrarSubMenu = null }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("DAM") },
                                    onClick = { }
                                )
                                DropdownMenuItem(
                                    text = { Text("DAW") },
                                    onClick = { }
                                )
                                DropdownMenuItem(
                                    text = { Text("ASIR") },
                                    onClick = { }
                                )
                            }
                        }

                        DropdownMenuItem(
                            text = { Text("CF Grado Medio") },
                            onClick = { mostrarSubMenu = "medio" },
                            leadingIcon = { Icon(Icons.Default.School, null) },
                            trailingIcon = { Icon(Icons.Default.ArrowRight, null) }
                        )
                        if (mostrarSubMenu == "medio") {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { mostrarSubMenu = null }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("SMR") },
                                    onClick = { }
                                )
                            }
                        }
                        DropdownMenuItem(
                            text = { Text("CF Grado Básica") },
                            onClick = { mostrarSubMenu = "basico" },
                            leadingIcon = { Icon(Icons.Default.School, null) },
                            trailingIcon = { Icon(Icons.Default.ArrowRight, null) }
                        )
                        if (mostrarSubMenu == "basico") {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { mostrarSubMenu = null }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Informatica de oficina") },
                                    onClick = { }
                                )
                                DropdownMenuItem(
                                    text = { Text("Informatica y comunicaciones") },
                                    onClick = { }
                                )
                            }
                        }
                        DropdownMenuItem(
                            text = { Text("Cursos de cualificacion") },
                            onClick = { mostrarSubMenu = "cursos" },
                            leadingIcon = { Icon(Icons.Default.School, null) },
                            trailingIcon = { Icon(Icons.Default.ArrowRight, null) }
                        )
                        if (mostrarSubMenu == "cursos") {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { mostrarSubMenu = null }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Videojuegos y realidad virtual") },
                                    onClick = { }
                                )
                                DropdownMenuItem(
                                    text = { Text("Ciberseguridad") },
                                    onClick = { }
                                )
                                DropdownMenuItem(
                                    text = { Text("Inteligencia artificial y big data") },
                                    onClick = { }
                                )
                            }
                        }

                    }
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    BadgedBox(
                        badge = {
                            Badge { Text(contadorNotificaciones.toString()) }
                        }
                    ) {
                        Icon(Icons.Default.ShoppingCart, "Carrito")
                    }
                }
            }
        )
        Button(
            onClick = { contadorNotificaciones++ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Comprar")
        }
    }
}

@Composable
fun BarraProgreso() {
    var progreso by remember { mutableStateOf(0.3f) }
    var mostrarProgresoCircular by remember { mutableStateOf(false) }
    var mostrarProgresoLineal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        LinearProgressIndicator(
            progress = progreso,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Blue
        )
        CircularProgressIndicator(
            progress = progreso,
            modifier = Modifier.size(60.dp),
            color = Color.Magenta
        )

        if (mostrarProgresoCircular) {
            CircularProgressIndicator(
                modifier = Modifier.size(60.dp)
            )
        }

        if (mostrarProgresoLineal) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                progreso = (progreso + 0.1f).coerceIn(0f, 1f)
            }) {
                Text("Incrementar")
            }

            Button(onClick = {
                progreso = (progreso - 0.1f).coerceIn(0f, 1f)
            }) {
                Text("Decrementar")
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { mostrarProgresoCircular = !mostrarProgresoCircular }) {
                Text("Acceder")
            }
            Button(onClick = { mostrarProgresoLineal = !mostrarProgresoLineal }) {
                Text("Enviar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Principal()
    }
}