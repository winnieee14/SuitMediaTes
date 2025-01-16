package com.example.suitmedia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        nameInput = findViewById(R.id.nameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.LoginButton)


        loginButton.setOnClickListener {
            val username = nameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()


            if (username.isEmpty() || password.isEmpty()) {
                showDialog("Error", "Username and password cannot be empty.")
                return@setOnClickListener
            }


            if (username == "alfagift-admin" && password == "asdf") {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("USER_NAME", username)
                startActivity(intent)
            } else {
                showDialog("Login Failed", "Invalid username or password.")
            }
        }
    }


    private fun showDialog(title: String, message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }
}
