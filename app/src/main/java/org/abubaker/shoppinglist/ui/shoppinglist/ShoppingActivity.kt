package org.abubaker.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.lifecycle.ViewModelProviders
import org.abubaker.shoppinglist.R
import org.abubaker.shoppinglist.data.db.ShoppingDatabase
import org.abubaker.shoppinglist.data.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {

    // onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shopping)
        
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)


        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

    }

}