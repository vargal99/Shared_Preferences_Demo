package com.example.android.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var nameText: TextView
    private lateinit var ageText: EditText
    private lateinit var helloButton: Button
    private lateinit var sf:SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloButton = findViewById(R.id.btnHello)
        nameText = findViewById(R.id.etName)
        ageText = findViewById(R.id.etAge)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()

        helloButton.setOnClickListener {
            Toast.makeText(this, "Hello ${nameText.text}, ${ageText.text}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onPause() {
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        editor.apply {
            putString("sf_name",name)
            putInt("sf_age",age)
            commit()
        }
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name",null)
        val age = sf.getInt("sf_age",0)
        nameText.setText(name)
        if(age!=0) {
            ageText.setText(age.toString())
        }
    }
}