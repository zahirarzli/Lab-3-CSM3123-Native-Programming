package com.example.sqlitedemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var viewButton: Button
    private lateinit var viewSortedButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        nameEditText = findViewById(R.id.et_name)
        ageEditText = findViewById(R.id.et_age)
        resultTextView = findViewById(R.id.tv_result)
        addButton = findViewById(R.id.btn_add)
        updateButton = findViewById(R.id.btn_update)
        deleteButton = findViewById(R.id.btn_delete)
        viewButton = findViewById(R.id.btn_view)
        viewSortedButton = findViewById(R.id.btn_view_sorted)

        addButton.setOnClickListener {
            addUser()
        }

        updateButton.setOnClickListener {
            updateUser()
        }

        deleteButton.setOnClickListener {
            deleteUser()
        }

        viewButton.setOnClickListener {
            viewUsers()
        }

        viewSortedButton.setOnClickListener {
            viewSortedUsers()
        }
    }

    private fun addUser() {
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString().toIntOrNull()

        if (name.isNotEmpty() && age != null) {
            val success = databaseHelper.addUser(name, age)
            Toast.makeText(this, if (success) "User added successfully!" else "Failed to add user", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter a valid name and age", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUser() {
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString().toIntOrNull()

        if (name.isNotEmpty() && age != null) {
            val success = databaseHelper.updateUser(name, age)
            Toast.makeText(this, if (success) "User updated successfully!" else "Failed to update user", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter a valid name and age", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteUser() {
        val name = nameEditText.text.toString()
        if (name.isNotEmpty()) {
            val success = databaseHelper.deleteUser(name)
            Toast.makeText(this, if (success) "User deleted successfully!" else "Failed to delete user", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewUsers() {
        val users = databaseHelper.getAllUsers()
        resultTextView.text = if (users.isNotEmpty()) users.joinToString("\n") else "No users found"
    }

    private fun viewSortedUsers() {
        val users = databaseHelper.getAllUsers(sorted = true)
        resultTextView.text = if (users.isNotEmpty()) users.joinToString("\n") else "No users found"
    }
}
