package com.example.filmesfavoritos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmesfavoritos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movies = mutableListOf<Movie>()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieAdapter(movies)
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewMovies.adapter = adapter

        updateEmptyState()

        binding.buttonAdd.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val director = binding.editTextDirector.text.toString().trim()

            if (title.isEmpty() || director.isEmpty()) {
                binding.editTextTitle.error = if (title.isEmpty()) getString(R.string.error_empty_fields) else null
                binding.editTextDirector.error = if (director.isEmpty()) getString(R.string.error_empty_fields) else null
                return@setOnClickListener
            }

            adapter.addMovie(Movie(title, director))
            binding.editTextTitle.text?.clear()
            binding.editTextDirector.text?.clear()
            binding.editTextTitle.requestFocus()
            updateEmptyState()
        }
    }

    private fun updateEmptyState() {
        binding.textViewEmpty.visibility = if (movies.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
    }
}
