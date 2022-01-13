package com.akozadaev.todo.room

import androidx.room.*


@Dao
interface ItemDao {

    @get:Query("SELECT * FROM item")
    val getAll: List<Item>

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM item")
    fun deleteAll()
}