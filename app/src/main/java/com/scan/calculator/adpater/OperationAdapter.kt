package com.scan.calculator.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scan.calculator.R
import com.scan.calculator.databinding.OperationItemBinding
import com.scan.calculator.utils.OnClickListener

class OperationAdapter : RecyclerView.Adapter<OperationAdapter.OperationViewHolder>() {

    private var operationList: ArrayList<Pair<String,Int>> = ArrayList()
    private lateinit var listener: OnClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.operation_item,parent,false)
        return OperationViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        val item = operationList[position]
        holder.bind(item.first,item.second)
    }

    override fun getItemCount(): Int {
        return operationList.size
    }

    fun setListener(listener: OnClickListener) {
        this.listener = listener
    }
     fun addItem(operator: String, value: Int) {
        operationList.add(Pair(operator,value))
        notifyDataSetChanged()
    }
    fun getItem(position: Int) : Pair<String, Int> {
       return operationList[operationList.size - position-1]
    }
    class OperationViewHolder(view: View, val listener: OnClickListener) : RecyclerView.ViewHolder(view) {
        private val binding = OperationItemBinding.bind(view)
        fun bind(operator:String, value:Int) {
            binding.operator.text = operator
            binding.value.text = value.toString()
            binding.root.setOnClickListener { listener.onOperationClicked(operator,value) }
        }
    }
}