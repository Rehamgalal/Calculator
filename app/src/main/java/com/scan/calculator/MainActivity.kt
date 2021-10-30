package com.scan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.scan.calculator.adpater.OperationAdapter
import com.scan.calculator.utils.Calculate.Companion.add
import com.scan.calculator.utils.Calculate.Companion.divide
import com.scan.calculator.utils.Calculate.Companion.minus
import com.scan.calculator.utils.Calculate.Companion.multiply
import com.scan.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operation:String = ""
    private var resultValue = 0
    private var undoCounter = 0
    private var redoCounter = 0
    private lateinit var adapter:OperationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupRecyclerView()
        onClick()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 5)
        adapter = OperationAdapter()
        binding.recyclerView.adapter = adapter
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
            adapter.addItem(operation,resultValue)
            operation = ""
            binding.equal.isClickable = false
            binding.resultValue.text = resultValue.toString()
            binding.inputValue.text.clear()
        }
    }
    private fun calculate(firstNum: Int, secNum:Int ): Int {
        return when(operation) {
            "+" -> add(firstNum,secNum)
            "-" -> minus(firstNum,secNum)
            "*" -> multiply(firstNum,secNum)
            "/" -> divide(firstNum,secNum)
            else -> firstNum
        }
    }
}