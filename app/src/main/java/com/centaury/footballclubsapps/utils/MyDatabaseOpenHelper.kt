package com.centaury.footballclubsapps.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.centaury.footballclubsapps.data.model.pinned.Pinned
import org.jetbrains.anko.db.*

/**
 * Created by Centaury on 10/10/2018.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "PinnedTeam.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getIntance(ctx: Context): MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Pinned.TABLE_PINNED, true,
                Pinned.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Pinned.EVENT_ID to TEXT + UNIQUE,
                Pinned.HOMETEAM_ID to TEXT,
                Pinned.HOMETEAM to TEXT,
                Pinned.AWAYTEAM_ID to TEXT,
                Pinned.AWAYTEAM to TEXT,
                Pinned.HOMESCORE to TEXT,
                Pinned.AWAYSCORE to TEXT,
                Pinned.DATEEVENT to TEXT
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Pinned.TABLE_PINNED, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getIntance(applicationContext)