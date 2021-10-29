package com.scan.calculator

class Calculate {

    companion object {

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

    }
}