package com.example.sharedpreferencesdemo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var clearButton: Button

    private fun saveName() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val name = nameEditText.text.toString()
        if (name.isNotEmpty()) {
            editor.putString("userName", name)
            editor.apply()
            greetingTextView.text = "Name saved!"
        } else {
            greetingTextView.text = "Please enter a name"
        }
    }

    private fun loadName(){
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("userName", "No name saved")
        greetingTextView.text = "Welcome, $savedName!"
    }

    private fun clearName(){
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("userName")
        editor.apply()
        greetingTextView.text = "Data cleared!"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        greetingTextView = findViewById(R.id.tv_greeting)
        nameEditText = findViewById(R.id.et_name)
        saveButton = findViewById(R.id.btn_save)
        loadButton = findViewById(R.id.btn_load)
        clearButton = findViewById(R.id.btn_clear)

        saveButton.setOnClickListener{
            saveName()
        }

        loadButton.setOnClickListener{
            loadName()
        }

        clearButton.setOnClickListener{
            clearName()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}