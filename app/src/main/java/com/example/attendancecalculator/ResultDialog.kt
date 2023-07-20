package com.example.attendancecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultDialog : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }


        val totalc = intent.getIntExtra("Extra_totalc", 0)
        val presentc = intent.getIntExtra("Extra_presentc", 0)
        val required = intent.getIntExtra("Extra_required", 0)
        val bunk = intent.getIntExtra("Extra_Bunk", 0)
        val cAttn: Float = (presentc.toFloat() / totalc.toFloat()) * 100
        val req = presentc + required
        val total: Int = totalc + required + bunk
        val nAttn: Float = (req.toFloat() / total.toFloat()) * 100





        if (bunk > 0 && required==0) {
            setContentView(R.layout.result_dialog0)
        } else if (required in 6..11) {
            setContentView(R.layout.result_dialog1)
        } else if (required in 12..17) {
            setContentView(R.layout.result_dialog2)
        } else if (required > 17) {
            setContentView(R.layout.result_dialog3)
        } else  {
            setContentView(R.layout.activity_result_dialog)

        }

        var result: String = ""


        if (required == 0 && bunk!=1) {
            result =
                    "You already have 75% attendance. \n" +
                    "You can bunk $bunk more classes and still have 75% attendance. \n " +
                    "Current Attendance : $presentc/$totalc = $cAttn% \n" +
                    "Attendance then : $presentc/$total = $nAttn%\n"

        }
        else if (required == 0 && bunk == 1){
            result =
                "You already have 75% attendance. \n" +
                        "You can bunk $bunk more class and still have 75% attendance. \n " +
                        "Current Attendance : $presentc/$totalc = $cAttn% \n" +
                        "Attendance then : $presentc/$total = $nAttn%"


        }
        else if (required == 1) {
            result =
                "You need to attend $required more class to attain 75% attendance \n" +
                        "Current Attendance : $presentc/$totalc = $cAttn% \n" +
                        "Required Attendance : $req/$total = $nAttn% \n"
        }
        else{
            result ="You need to attend $required more classes to attain 75% attendance \n" +
                    "Current Attendance : $presentc/$totalc = $cAttn% \n" +
                    "Required Attendance : $req/$total = $nAttn%"
        }

        val tvResult = findViewById<TextView>(R.id.tvResult)
        tvResult.text=result

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            finish()
        }


    }
}
