package org.abubaker.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.abubaker.shoppinglist.data.db.entities.ShoppingItem

// suspend = coroutine implementation
@Dao
interface ShoppingDao {

    // It takes a shopping item as it is a mix of insert / update. If the item is not available
    // then it will update itself, otherwise a new record will be created.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    // Delete
    @Delete
    suspend fun delete(item: ShoppingItem)

    // Query: Select all records from the table
    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>

}