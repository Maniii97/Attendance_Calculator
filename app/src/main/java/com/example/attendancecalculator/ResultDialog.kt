package com.example.attendancecalculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlin.math.ceil

class ResultDialog : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }



        val totalc = intent.getIntExtra("Extra_totalc",0)
        val presentc = intent.getIntExtra("Extra_presentc",0)
        val required = intent.getIntExtra("Extra_required",0)
        val bunk = intent.getIntExtra("Extra_Bunk",0)
        var cAttn : Float = (presentc.toFloat() / totalc.toFloat())*100
        var req = presentc+required
        val total : Int = totalc+required+bunk
        var nAttn : Float = (req.toFloat()/total.toFloat())*100





        if(required == 0){
            setContentView(R.layout.result_dialog0)
        }

        if(required in 6..11 ){
            setContentView(R.layout.result_dialog1)
        }
        else if(required in 12..17){
            setContentView(R.layout.result_dialog2)
        }
        else if(required>17){
            setContentView(R.layout.result_dialog3)
        }
        else{
            setContentView(R.layout.activity_result_dialog)

        }

        var result : String =""



        result = if(required == 0){
            "You already have 75% attendance \n" +
                    "you can bunk $bunk  more classes and still have 75% attendance \n "+
                    "Current Attendance : $presentc / $totalc = $cAttn% \n" +
                    "Attendance then : $presentc / $total = $nAttn%\n"

        } else {
            "You need to attend $required more classes to attain 75% attendance \n" +
                    "Current Attendance : $presentc / $totalc = $cAttn% \n" +
                    "Required Attendance : $req / $total = $nAttn% \n"
        }




        val tvResult = findViewById<TextView>(R.id.tvResult)
        tvResult.text=result

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            finish()
        }


    }
}
