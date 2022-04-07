package org.abubaker.shoppinglist.ui.shoppinglist

import org.abubaker.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}