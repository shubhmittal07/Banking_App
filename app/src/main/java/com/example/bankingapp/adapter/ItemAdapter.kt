package com.example.bankingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankingapp.R
import com.example.bankingapp.databinding.ListItemCustBinding
import com.example.bankingapp.model.Customer

class ItemAdapter(private val context:Context,
                  private var dataset : List<Customer>):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position:Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class ItemViewHolder(val binding:ListItemCustBinding,listener: onItemClickListener):RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener{
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCustBinding.inflate(layoutInflater,parent,false)
        return ItemViewHolder(binding,mListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val view = dataset[position]
        holder.binding.apply{
            name.text = view.name
            phoneNo.text = view.phone.toString()
            balance.text = view.balance.toString()
        }

    }
    override fun getItemCount() = dataset.size
}