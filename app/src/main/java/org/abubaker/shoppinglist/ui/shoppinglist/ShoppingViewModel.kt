package org.abubaker.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.abubaker.shoppinglist.data.db.entities.ShoppingItem
import org.abubaker.shoppinglist.data.repositories.ShoppingRepository

// Execute functions in the Main Thread using the CoroutineScope
class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    // upsert() - Coroutine
    fun upsert(item: ShoppingItem) =
        CoroutineScope(Dispatchers.Main).launch { repository.upsert(item) }

    // delete() - Coroutine
    fun delete(item: ShoppingItem) =
        CoroutineScope(Dispatchers.Main).launch { repository.delete(item) }

    // Query - No need to execute it using Coroutine
    fun getAllShoppingItems() = repository.getAllShoppingItems()

}