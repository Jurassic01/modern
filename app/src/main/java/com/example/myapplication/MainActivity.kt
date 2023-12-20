package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppContent()
        }
    }

    @Composable
    fun MyAppContent() {
        val tasks = remember { mutableStateListOf<String>() }
        var text by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Введіть текст") }
            )

            Button(onClick = {
                tasks.add(text)
                text = ""
            }) {
                Text("Додати")
            }

            Spacer(modifier = Modifier.height(16.dp))

            tasks.forEach { task ->
                Row {
                    Text(task, modifier = Modifier.weight(1f))
                    Button(onClick = {
                        tasks.remove(task)
                    }) {
                        Text("Видалити")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
