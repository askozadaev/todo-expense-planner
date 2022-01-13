package com.akozadaev.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akozadaev.todo.room.Item

class TodoAdapter() : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var list:List<Item> = ArrayList()

    fun setItems(items:List<Item>){
        list = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TodoViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val text = view.findViewById<TextView>(R.id.item_)

        fun bind(item:Item){
            text.text = item.name + " - " + item.price + " руб."
        }

    }
}