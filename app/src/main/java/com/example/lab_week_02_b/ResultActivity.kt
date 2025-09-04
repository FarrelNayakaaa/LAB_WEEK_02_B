package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
        val backButton = findViewById<Button>(R.id.back_button)

        val colorCode = intent.getStringExtra(MainActivity.COLOR_KEY)

        if (colorCode.isNullOrBlank()) {
            Intent().let { errorIntent ->
                errorIntent.putExtra(MainActivity.ERROR_KEY, true)
                setResult(Activity.RESULT_OK, errorIntent)
                finish()
                return
            }
        }

        try {
            backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            resultMessage.text = getString(R.string.color_code_result_message, colorCode.uppercase())
        } catch (ex: IllegalArgumentException) {
            Intent().let { errorIntent ->
                errorIntent.putExtra(MainActivity.ERROR_KEY, true)
                setResult(Activity.RESULT_OK, errorIntent)
                finish()
                return
            }
        }

        backButton.setOnClickListener {
            finish() // kembali ke MainActivity
        }
    }
}
