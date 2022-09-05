package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startbutton = findViewById<Button>(R.id.startbutton)
        val nameinput = findViewById<EditText>(R.id.nameinput)

        

        startbutton.setOnClickListener {
            if (nameinput.text.isEmpty())
                Toast.makeText(this, "Please Enter a Valid Name", Toast.LENGTH_LONG).show()
            else {
                Toast.makeText(this, "Hello ${nameinput.text}!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, nameinput.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}