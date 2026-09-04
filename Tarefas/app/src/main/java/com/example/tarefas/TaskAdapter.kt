package com.example.tarefas

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tarefas.databinding.ItemTaskBinding

class TaskAdapter(private val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        val context = holder.itemView.context

        holder.binding.textViewName.text = task.name
        holder.binding.textViewDescription.text = task.description

        if (task.isDone) {
            holder.binding.textViewName.paintFlags =
                holder.binding.textViewName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.buttonComplete.text = context.getString(R.string.button_completed)
            holder.binding.buttonComplete.isEnabled = false
            holder.binding.root.setCardBackgroundColor(ContextCompat.getColor(context, R.color.done_background))
        } else {
            holder.binding.textViewName.paintFlags =
                holder.binding.textViewName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.binding.buttonComplete.text = context.getString(R.string.button_complete)
            holder.binding.buttonComplete.isEnabled = true
            holder.binding.root.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_background))
        }

        holder.binding.buttonComplete.setOnClickListener {
            task.isDone = true
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
}
