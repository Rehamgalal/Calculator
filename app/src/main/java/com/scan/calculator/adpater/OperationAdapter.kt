package com.scan.calculator.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scan.calculator.R
import com.scan.calculator.databinding.OperationItemBinding

class OperationAdapter : RecyclerView.Adapter<OperationAdapter.OperationViewHolder>() {

    private var operationList: ArrayList<Pair<String,Int>> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.operation_item,parent,false)
        return OperationViewHolder(view)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        val item = operationList[position]
        holder.bind(item.first,item.second)
    }

    override fun getItemCount(): Int {
        return operationList.size
    }

     fun addItem(operator: String, value: Int) {
        operationList.add(Pair(operator,value))
        notifyDataSetChanged()
    }
    fun getItem(position: Int) : Pair<String, Int>{
       return operationList[operationList.size - position-1]
    }
    class OperationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = OperationItemBinding.bind(view)
        fun bind(operator:String, value:Int) {
            binding.operator.text = operator
            binding.value.text = value.toString()
        }
    }
}