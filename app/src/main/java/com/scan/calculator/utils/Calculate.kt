package com.scan.calculator.utils

class Calculate {

    companion object {

        var resultValue = 0
        var undoCounter = 0
        var redoCounter = 0
        var operationCounter = 0

        fun add(firstNum: Int, secNum:Int) : Int {
            return firstNum+secNum
        }
        fun minus(firstNum: Int, secNum:Int) : Int {
            return firstNum-secNum
        }
        fun multiply(firstNum: Int, secNum:Int) : Int {
            return firstNum*secNum
        }
        fun divide(firstNum: Int, secNum:Int) : Int {
            return firstNum/secNum
        }
         fun reverse(firstNum: Int,item:Pair<String,Int>):Pair<String,Int>{
            val operation =  when(item.first) {
                "+" -> "-"
                "-" -> "+"
                "*"->"/"
                "/"->"*"
                else -> ""
            }
            return Pair(operation,calculate(firstNum,item.second,operation))
        }
         fun calculate(firstNum: Int, secNum:Int,operation:String): Int {
             operationCounter ++
             return when(operation) {
                "+" -> add(firstNum,secNum)
                "-" -> minus(firstNum,secNum)
                "*" -> multiply(firstNum,secNum)
                "/" -> divide(firstNum,secNum)
                else -> firstNum
            }
        }

    }
}