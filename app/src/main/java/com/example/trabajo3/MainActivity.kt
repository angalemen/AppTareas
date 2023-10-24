package com.example.trabajo3

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabajo3.ui.theme.Trabajo3Theme
import androidx.compose.ui.text.TextStyle



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Trabajo3Theme {
                TareasApp()
            }
        }
    }
}
data class Tarea(val id: Int, val descripcion: String, var completada: Boolean = false)


val listaTareas = List(20) { Tarea(it + 1, "Tarea ${it + 1}", false) }.toMutableStateList()




@Composable
fun ListaDeTareas(tareas: List<Tarea>, onTareaCompletada: (Tarea) -> Unit, onTareaBorrada: (Tarea) -> Unit) {
    LazyColumn {
        items(tareas) { tarea ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = tarea.completada,
                    onCheckedChange = { isChecked ->
                        tarea.completada = isChecked
                        onTareaCompletada(tarea)
                    }
                )
                Text(
                    text = tarea.descripcion,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.primary

                    )
                )
                IconButton(
                    onClick = { onTareaBorrada(tarea) },
                    modifier = Modifier.size(30.dp),

                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar", tint = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
@Composable
fun TareasApp() {
    val tareas by remember { mutableStateOf(listaTareas) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {

            ListaDeTareas(
                tareas = tareas,
                onTareaCompletada = { tarea ->
                    tarea.completada = !tarea.completada
                },
                onTareaBorrada = { tarea ->
                    tareas.remove(tarea)
                }
            )
        }
    }
}