package com.example.lab6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint

class Activity1 : AppCompatActivity() {
    var colors = arrayOf(0xFF009688,0xFFFFEB3B,0xFF03A9F4,0xFF87CEEB)
    var sheetNumber = 0
    lateinit var text: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
        text = findViewById(R.id.text)
        sheetNumber = getIntent().getIntExtra("sheetNumber", 0)
        val next : Button = findViewById(R.id.next)
        next.setOnClickListener(){
            if(sheetNumber < colors.size - 1){
                val intent = Intent(this,this::class.java)
                intent.putExtra("sheetNumber",sheetNumber+1)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Это последняя страница", Toast.LENGTH_SHORT).show()
            }
        }
        var textSizeCounter = 18
        val minus : Button = findViewById(R.id.minus)
        minus.setOnClickListener(){
            textSizeCounter-=2
            text.textSize = textSizeCounter.toFloat()
        }
        val plus : Button = findViewById(R.id.plus)
        plus.setOnClickListener(){
            textSizeCounter+=2
            text.textSize = textSizeCounter.toFloat()
        }
    }

    override fun onPause() {
        super.onPause()
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        prefs.putString("note1", text.editableText.toString())
        prefs.apply()
    }

    override fun onResume() {
        super.onResume()
        val sheet : ConstraintLayout = findViewById(R.id.sheet)
        sheet.setBackgroundColor(colors[sheetNumber].toInt())
        val saveState =
            getPreferences(Context.MODE_PRIVATE).getString("note1",null)
        if (saveState != null)
            text.setText(saveState)
    }
}