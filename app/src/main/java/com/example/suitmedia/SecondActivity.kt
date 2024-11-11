package com.example.suitmedia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val userName = intent.getStringExtra("USER_NAME") ?: "User"


        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val userNameTextView = findViewById<TextView>(R.id.userNameTextView)
        val selectedUserTextView = findViewById<TextView>(R.id.selectedUserTextView)
        val chooseUserButton = findViewById<Button>(R.id.chooseUserButton)


        welcomeTextView.text = "Welcome"
        userNameTextView.text = userName
        selectedUserTextView.text = "Selected User $userName"


        chooseUserButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
