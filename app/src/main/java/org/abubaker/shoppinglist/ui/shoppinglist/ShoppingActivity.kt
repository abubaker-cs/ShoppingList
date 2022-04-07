package org.abubaker.shoppinglist.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import org.abubaker.shoppinglist.R
import org.abubaker.shoppinglist.data.db.ShoppingDatabase
import org.abubaker.shoppinglist.data.db.entities.ShoppingItem
import org.abubaker.shoppinglist.data.repositories.ShoppingRepository
import org.abubaker.shoppinglist.databinding.ActivityShoppingBinding
import org.abubaker.shoppinglist.other.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    // onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Inflate the activity_main.xml file
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping)
        setContentView(binding.root)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }


}