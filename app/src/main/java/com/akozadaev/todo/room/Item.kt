package com.akozadaev.todo.room

import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity
class Item {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var name: String? = null
    var price = 0
}