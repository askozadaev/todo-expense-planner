package com.akozadaev.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akozadaev.todo.room.AppDatabase
import com.akozadaev.todo.room.Item
import com.akozadaev.todo.room.ItemDao


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TodoAdapter
    lateinit var price: EditText
    lateinit var name: EditText
    lateinit var add: ImageView
    lateinit var sum: TextView
    lateinit var reset: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        price = findViewById<EditText>(R.id.price_item)
        name = findViewById<EditText>(R.id.name_item)
        add = findViewById<ImageView>(R.id.add_item)
        sum = findViewById<TextView>(R.id.textView)
        reset = findViewById<ImageView>(R.id.reset)


        val db: AppDatabase = ThiIsApplication.instance!!.getDatabase()!!
        val employeeDao: ItemDao? = db.employeeDao()
        initRv()
        updateUi(employeeDao!!.getAll)

        add.setOnClickListener {
            if(name.text.toString().isNotEmpty() && price.text.toString().isNotEmpty()) {
                val item = Item()
                item.name = name.text.toString()
                item.price = price.text.toString().toInt()
                employeeDao!!.insert(item)
                updateUi(employeeDao.getAll)
            }else{
                Toast.makeText(this,"Необходимо заполнить поля",Toast.LENGTH_LONG).show()
            }
        }
        reset.setOnClickListener {
            employeeDao!!.deleteAll()
            updateUi(employeeDao.getAll)
        }
    }

    fun initRv(){
        adapter = TodoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    fun getSum(list:List<Item>):Int{
        var sum = 0
        list.map {
            sum += it.price
        }
        return sum
    }

    fun updateUi(list:List<Item>){
        adapter.setItems(list)
        sum.text = getSum(list).toString() + " руб"
    }

}