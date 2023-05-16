package com.example.attendancecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.ceil
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        var bunk = 0
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val presentcls = findViewById<EditText>(R.id.presentcls)
        val totalcls = findViewById<EditText>(R.id.totalcls)
        var required : Int


        btnCalculate.setOnClickListener {
            if (presentcls.text.toString().isEmpty() || totalcls.text.toString()
                    .isEmpty() || totalcls.text.toString().toInt() == 0 || totalcls.text.toString()
                    .toInt() < presentcls.text.toString().toInt()) {
                Toast.makeText(this, "Please Enter a Valid Input", Toast.LENGTH_LONG).show()
            }


            else {
                val presentc = presentcls.text.toString().toInt()
                val totalc = totalcls.text.toString().toInt()

                if (presentc < (ceil(0.75 * totalc).toInt())) {
                     required = ceil(((0.75 * totalc) - presentc) / 0.25).toInt()
                }
                else {
                    required = 0
                    bunk = floor(((100*presentc - 75*totalc)/75).toDouble()).toInt()
                }

                Intent(this, ResultDialog::class.java).also {
                    it.putExtra("Extra_presentc", presentc)
                    it.putExtra("Extra_totalc", totalc)
                    it.putExtra("Extra_required", required)
                    it.putExtra("Extra_Bunk", bunk)
                    startActivity(it)


                }
            }
        }
    }
}

