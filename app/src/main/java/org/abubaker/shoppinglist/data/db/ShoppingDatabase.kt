package org.abubaker.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.abubaker.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class], // Reference to Entity file
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    //
    abstract fun getShoppingDao(): ShoppingDao

    //
    companion object {

        // Volatile = Rights to this instance will be made visible instantly to other threads.
        // It avoids duplicated access to the file.
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        // It will be invoked whenever the ShoppingDatabase() function will be initialized.
        // synchronized(LOCK)  = No other threads will access this instance at the same time.
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            //
            instance ?: createDatabase(context).also { instance = it }
        }

        // createDatabase
        private fun createDatabase(context: Context) = Room.databaseBuilder(

            // Get application context
            context.applicationContext,

            // Create ShoppingDB.db file
            ShoppingDatabase::class.java, "ShoppingDB.db"

        ).build()
    }
}