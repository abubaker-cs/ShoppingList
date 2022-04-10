package org.abubaker.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.abubaker.shoppinglist.R
import org.abubaker.shoppinglist.data.db.entities.ShoppingItem
import org.abubaker.shoppinglist.databinding.ShoppingItemBinding
import org.abubaker.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder>() {


    // ViewHolder will require 3 Members

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    inner class ViewHolder(view: ShoppingItemBinding) : RecyclerView.ViewHolder(view.root) {

        val tvName = view.tvName
        val tvAmount = view.tvAmount

        // Notes
        val ivDelete = view.ivDelete
        val ivPlus = view.ivPlus
        val ivMinus = view.ivMinus

    }


    // Member 1/3 - Inflate the XML file: shopping_item.xml
    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */


    // onCreateViewHolder()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ShoppingItemBinding =
            ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // Member 2/3 - How each item will look like?
    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val curShoppingItem = items[position]

        holder.tvName.text = curShoppingItem.name
        holder.tvAmount.text = "${curShoppingItem.amount}"

        holder.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.ivMinus.setOnClickListener {
            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    // Member 3/3
    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }
}