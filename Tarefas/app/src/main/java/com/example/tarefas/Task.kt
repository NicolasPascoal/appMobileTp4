package com.example.tarefas

data class Task(
    val name: String,
    val description: String,
    var isDone: Boolean = false
)
