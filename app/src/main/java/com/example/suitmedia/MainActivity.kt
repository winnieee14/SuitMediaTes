package com.example.suitmedia
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var palindromeInput: EditText
    private lateinit var checkButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        nameInput = findViewById(R.id.nameInput)
        palindromeInput = findViewById(R.id.palindromeInput)
        checkButton = findViewById(R.id.checkButton)
        nextButton = findViewById(R.id.nextButton)


        checkButton.setOnClickListener {
            val inputText = palindromeInput.text.toString()
            if (inputText.isNotEmpty()) {
                if (isPalindrome(inputText)) {
                    showDialog("Palindrome", "The input text is a palindrome!")
                } else {
                    showDialog("Not a Palindrome", "The input text is not a palindrome.")
                }
            } else {
                showDialog("Error", "Please enter text to check.")
            }
        }


        nextButton.setOnClickListener {
            val name = nameInput.text.toString()
            if (name.isNotEmpty()) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("USER_NAME", name)
                startActivity(intent)
            } else {
                showDialog("Error", "Please enter your name before proceeding.")
                }
            }
    }


    private fun isPalindrome(text: String): Boolean {
        val cleanedText = text.replace("\\s".toRegex(), "").lowercase()
        return cleanedText == cleanedText.reversed()
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
