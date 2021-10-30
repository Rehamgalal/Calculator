package com.scan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.GridLayoutManager
import com.scan.calculator.adpater.OperationAdapter
import com.scan.calculator.databinding.ActivityMainBinding
import com.scan.calculator.utils.Calculate.Companion.calculate
import com.scan.calculator.utils.Calculate.Companion.operationCounter
import com.scan.calculator.utils.Calculate.Companion.reverse
import com.scan.calculator.utils.OnClickListener

class MainActivity : AppCompatActivity() , OnClickListener{

    private lateinit var binding: ActivityMainBinding
    private var operation: String = ""
    private var resultValue = 0
    private var undoCounter = 0
    private var redoCounter = 0
    private lateinit var adapter: OperationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupRecyclerView()
        setupView()
        onClick()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        adapter = OperationAdapter()
        adapter.setListener(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupView() {
        binding.redo.isEnabled = false
        binding.undo.isEnabled = false
        binding.equal.isEnabled = false
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
            if (undoCounter < operationCounter) {
                val item = adapter.getItem(undoCounter)
                applyOperation(item)
                redoCounter = 0
                undoCounter += 2
                binding.redo.isEnabled = true
            } else {
                binding.undo.isEnabled = false
            }
        }
        binding.redo.setOnClickListener {
            if (undoCounter != 0) {
                val item = adapter.getItem(redoCounter)
                applyOperation(item)
                undoCounter -= 2
                redoCounter += 2
                binding.undo.isEnabled = true
            }
        }

        binding.inputValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (operation != "" && s.toString() != "") {
                    binding.equal.isEnabled = true
                } else {
                    binding.inputValue.text.clear()
                }
            }
        })
        binding.equal.setOnClickListener {
            val secNum = binding.inputValue.text.toString().toInt()
            resultValue = calculate(resultValue, secNum, operation)
            adapter.addItem(operation, secNum)
            binding.resultValue.text = resultValue.toString()
            clearData()
        }
    }

    private fun clearData() {
        redoCounter = 0
        undoCounter = 0
        operation = ""
        binding.equal.isEnabled = false
        binding.undo.isEnabled = true
        binding.redo.isEnabled = false
        binding.inputValue.text.clear()
    }

    private fun applyOperation(item: Pair<String, Int>) {
        val reversed = reverse(resultValue, item)
        resultValue = reversed.second
        adapter.addItem(reversed.first, item.second)
        binding.resultValue.text = resultValue.toString()
    }

    override fun onOperationClicked(operation: String, value: Int) {
        applyOperation(Pair(operation, value))
        redoCounter = 0
        undoCounter = 0
    }
}