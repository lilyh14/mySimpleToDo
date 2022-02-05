package caren.example.mysimpletodo

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// Bridge that tells the recyclerview how to display the data we give it
class TaskItemAdapter(val listOfItems: List<String>, val longClickListener: OnLongClickListener) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        //To change body of created functions use File | Settings | File Templates.
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)

        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
       //To change body of created functions use File | Settings | File Templates.
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       //To change body of created functions use File | Settings | File Templates.

        // get data model base on position
        val item = listOfItems.get(position)

        holder.textView.text = item

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Will store ref to elements in our layout view
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener{
             longClickListener.onItemLongClicked(adapterPosition)
                true
            }

        }
    }
}