package com.scan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.scan.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operation:String = ""
    private var resultValue:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        onClick()
    }

    private fun onClick() {
        binding.plus.setOnClickListener {
            operation = "+"
        }
        binding.minus.setOnClickListener {
            operation = "-"
        }
        binding.multiply.setOnClickListener {
            operation = "*"
        }
        binding.division.setOnClickListener {
            operation = "/"
        }
        binding.undo.setOnClickListener {

        }
        binding.redo.setOnClickListener {

        }
        binding.inputValue.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (operation != "" && s.toString() != ""){
                    binding.equal.isClickable = true
                } else {
                    binding.inputValue.text.clear()
                }
            }
        })
        binding.equal.setOnClickListener {

            resultValue = calculate(resultValue, binding.inputValue.text.toString().toInt())
            operation = ""
            binding.equal.isClickable = false
            binding.resultValue.text = resultValue.toString()
            binding.inputValue.text.clear()
        }
    }
    private fun calculate(firstNum: Int, secNum:Int ): Int {
        return when(operation) {
            "+" -> firstNum+secNum
            "-" -> firstNum-secNum
            "*" -> firstNum*secNum
            "/" -> firstNum/secNum
            else -> firstNum
        }
    }
}