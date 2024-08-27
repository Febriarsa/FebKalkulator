package com.example.kalkulator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.febkalkulator.R

class MainActivity : AppCompatActivity() {

    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)
    }

    fun onDigit(view: View) {
        tvExpression.append((view as Button).text)
    }

    fun onOperator(view: View) {
        tvExpression.append(" ${(view as Button).text} ")
    }

    fun onClear(view: View) {
        tvExpression.text = ""
        tvResult.text = ""
    }

    fun onEqual(view: View) {
        try {
            val expression = tvExpression.text.toString()
            val result = evaluateExpression(expression)
            tvResult.text = result.toString()
        } catch (e: Exception) {
            tvResult.text = "Error"
        }
    }

    fun onBackspace(view: View) {
        val expression = tvExpression.text.toString()
        if (expression.isNotEmpty()) {
            tvExpression.text = expression.substring(0, expression.length - 1)
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val tokens = expression.split(" ")
        var result = tokens[0].toDouble()

        var i = 1
        while (i < tokens.size) {
            when (tokens[i]) {
                "+" -> result += tokens[i + 1].toDouble()
                "-" -> result -= tokens[i + 1].toDouble()
                "*" -> result *= tokens[i + 1].toDouble()
                "/" -> result /= tokens[i + 1].toDouble()
            }
            i += 2
        }
        return result
    }
}
