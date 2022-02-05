package caren.example.mysimpletodo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()

    lateinit var adapter: TaskItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                // remove item from list
                listOfTasks.removeAt(position)

                // notify adapter that our dtaa has changed/updated
                adapter.notifyDataSetChanged()

                saveItems()
            }

        }

        loadItems()

        // Look up recyclerView in layout
       val recyclerView =  findViewById<RecyclerView>(R.id.recyclerView)

        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // set up button and input field so user can enter a task
        val inputTaskField = findViewById<EditText>(R.id.addTaskField)

        // get ref to button + set onclicklistener
        findViewById<Button>(R.id.button).setOnClickListener{
            // grab text the user has inputtined to the addtask field
                val userInputtedTask =inputTaskField.text.toString()
            // add the string to our list of tasks
            listOfTasks.add(userInputtedTask)

            // notify adapter that our data has been udpated
            adapter.notifyItemInserted(listOfTasks.size -1)

            // reset text field
            inputTaskField.setText("")

            saveItems()

        }
    }

    // get file
    fun getDataFile() : File {

        // every line is going to rep a specific task in our list of tasks
        return File(filesDir, "data.txt")
    }

    // load the items by reading every line in the file
    fun loadItems(){
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch(ioException: IOException){
            ioException.printStackTrace()
        }

    }

    // save item by writing to file
    fun saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        }catch(ioException: IOException){
            ioException.printStackTrace()
        }
    }

}
