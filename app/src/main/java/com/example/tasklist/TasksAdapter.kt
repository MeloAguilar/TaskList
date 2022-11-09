package com.example.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TasksAdapter (
    val tasks:List<TaskEntity>,
    val checkTask:(TaskEntity) -> Unit,
    val deleteTask:(TaskEntity) -> Unit): RecyclerView.Adapter<TasksAdapter.ViewHolder>(){

        override fun onBindViewHolder(holder: ViewHolder, position :Int){
            var item = tasks[position]
            holder.bind(item, checkTask, deleteTask)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvTask = view.findViewById<TextView>(R.id.tvTask)
        val cbIsDone = view.findViewById<CheckBox>(R.id.cbIsDone)


        fun bind(task: TaskEntity, checkTsk: (TaskEntity) -> Unit, deleteTaskEntity: (TaskEntity) -> Unit){
            tvTask.text = task.name
            cbIsDone.isChecked = task.isDone
            cbIsDone.setOnClickListener {checkTsk(task)}
            itemView.setOnClickListener{deleteTaskEntity(task)}
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}