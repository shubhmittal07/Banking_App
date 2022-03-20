package com.example.bankingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankingapp.databinding.ListItemCustBinding
import com.example.bankingapp.databinding.TransactionItemLayoutBinding
import com.example.bankingapp.model.Transaction

class TransactionItemAdapter(
    private val context: Context,
    private var dataset: List<Transaction>
) :
    RecyclerView.Adapter<TransactionItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: TransactionItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TransactionItemLayoutBinding.inflate(layoutInflater,parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val view = dataset[position]
        holder.binding.apply{
            date.text = view.date
            fromName.text = view.FromName
            toName.text = view.toName
            amount.text = view.amount
            status.text = view.status
        }
    }

    override fun getItemCount(): Int = dataset.size

}
