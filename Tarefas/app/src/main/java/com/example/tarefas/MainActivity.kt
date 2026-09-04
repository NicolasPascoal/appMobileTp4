package com.example.tarefas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarefas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<Task>()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TaskAdapter(tasks)
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTasks.adapter = adapter

        updateEmptyState()

        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()

            if (name.isEmpty() || description.isEmpty()) {
                binding.editTextName.error = if (name.isEmpty()) getString(R.string.error_empty_fields) else null
                binding.editTextDescription.error = if (description.isEmpty()) getString(R.string.error_empty_fields) else null
                return@setOnClickListener
            }

            adapter.addTask(Task(name, description))
            binding.editTextName.text?.clear()
            binding.editTextDescription.text?.clear()
            binding.editTextName.requestFocus()
            updateEmptyState()
        }
    }

    private fun updateEmptyState() {
        binding.textViewEmpty.visibility = if (tasks.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
    }
}
