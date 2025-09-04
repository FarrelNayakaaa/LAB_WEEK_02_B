package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val error = data?.getBooleanExtra(ERROR_KEY, false)

            if (error == true) {
                Toast.makeText(this, getString(R.string.color_code_input_invalid), Toast.LENGTH_LONG).show()
            } else {
                // kosongkan input setelah kembali
                findViewById<TextInputEditText>(R.id.color_code_input_field).setText("")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorCodeField = findViewById<TextInputEditText>(R.id.color_code_input_field)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val colorCode = colorCodeField.text.toString()

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra(COLOR_KEY, colorCode)
            }
            startForResult.launch(intent)
        }
    }
}
