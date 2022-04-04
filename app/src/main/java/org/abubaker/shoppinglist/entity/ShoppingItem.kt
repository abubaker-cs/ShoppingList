package org.abubaker.shoppinglist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// This will hold DATA (Table Structure)
@Entity(tableName = "shopping_items")
data class ShoppingItem(

    // Name
    @ColumnInfo(name = "item_name")
    var name: String,

    // Amount
    @ColumnInfo(name = "item_amount")
    var amount: Int

) {

    // Primary Key (Unique Column)
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}