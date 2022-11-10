package com.example.tasklist


import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var tasks: MutableList<TaskEntity>
    lateinit var adapter: TasksAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tasks = ArrayList()
        getTasks()
        findViewById<Button>(R.id.btnAddTask).setOnClickListener {
            addTask(TaskEntity(name = findViewById<EditText>(R.id.etTask).text.toString()))
        }
    }

    fun getTasks() = runBlocking{
        launch{
            tasks = MisNotasApp.database.taskDao().getAll()
            setUpRecyclerView(tasks)
            Log.d("Pruebas", tasks.toString())
        }

    }




    fun setUpRecyclerView(tasks: MutableList<TaskEntity>) {
        adapter = TasksAdapter(tasks, {updateTask(it)}, {deleteTask(it)})
        recyclerView = findViewById(R.id.rvTask)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }



    fun updateTask(task : TaskEntity) = runBlocking{
        launch {
            task.isDone = !task.isDone
            MisNotasApp.database.taskDao().updateTask(task)
        }
    }



    fun deleteTask(task : TaskEntity) = runBlocking {
        launch{
            val pos = tasks.indexOf(task)
            MisNotasApp.database.taskDao().deleteTask(task)
            tasks.remove(task)
            adapter.notifyItemRemoved(pos)
        }
    }



    fun clearFocus(){
        findViewById<EditText>(R.id.etTask).setText("")
    }



    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }




    fun addTask(task:TaskEntity) = runBlocking {
        launch {
            val id=  MisNotasApp.database.taskDao().addTask(task)
            val recoveryTask = MisNotasApp.database.taskDao().getTaskById(id)
            tasks.add(recoveryTask)
            adapter.notifyItemInserted(tasks.size)

            Log.d("Pruebas", id.toString())
            clearFocus()
            hideKeyboard()

        }
    }




}