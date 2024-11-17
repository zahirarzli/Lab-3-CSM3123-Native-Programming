package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "Users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_AGE = "age"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_AGE INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(name: String, age: Int): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_AGE, age)
        }
        val result = db.insert(TABLE_USERS, null, contentValues)
        db.close()
        return result != -1L
    }

    fun updateUser(name: String, age: Int): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply { put(COLUMN_AGE, age) }
        val result = db.update(TABLE_USERS, contentValues, "$COLUMN_NAME=?", arrayOf(name))
        db.close()
        return result > 0
    }

    fun deleteUser(name: String): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_USERS, "$COLUMN_NAME=?", arrayOf(name))
        db.close()
        return result > 0
    }

    fun getAllUsers(sorted: Boolean = false): List<String> {
        val userList = ArrayList<String>()
        val db = readableDatabase
        val sortOrder = if (sorted) "$COLUMN_NAME ASC" else null
        val cursor = db.query(TABLE_USERS, null, null, null, null, null, sortOrder)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                userList.add("Name: $name, Age: $age")
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }
}
