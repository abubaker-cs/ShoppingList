package org.abubaker.shoppinglist.data.repositories

import org.abubaker.shoppinglist.data.db.ShoppingDatabase
import org.abubaker.shoppinglist.data.db.entities.ShoppingItem

//
class ShoppingRepository(

    // Reference to the targeted Database
    private val db: ShoppingDatabase

) {

    // upsert()
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    // delete()
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    // Query = getAllShoppingItems()
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()

}