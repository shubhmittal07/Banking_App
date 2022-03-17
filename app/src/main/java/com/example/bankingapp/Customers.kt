package com.example.bankingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.Transition
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankingapp.adapter.ItemAdapter
import com.example.bankingapp.databinding.ActivityCustomersBinding
import com.example.bankingapp.databinding.ListItemCustBinding
import com.example.bankingapp.dbHelper.DBhelper
import com.example.bankingapp.model.Customer

class Customers : AppCompatActivity(){
    private lateinit var dbHelper:DBhelper
    private lateinit var binding:ActivityCustomersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dbHelper = DBhelper(this)
        viewCustomer()
    }
    private fun viewCustomer(){
        val customersList= dbHelper.getCustomers(this)
        Log.d("DebugTag",customersList.size.toString())
        val adapter = ItemAdapter(this,customersList)
        val rv = binding.recycle01
        rv.adapter = adapter
        adapter.setOnItemClickListener(object: ItemAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@Customers,CustomerDetails::class.java)
                intent.putExtra("name",customersList[position].name)
                intent.putExtra("phone",customersList[position].phone)
                intent.putExtra("balance",customersList[position].balance)
                intent.putExtra("email",customersList[position].email)
                intent.putExtra("accNo",customersList[position].AccountNo)
                intent.putExtra("ifsc",customersList[position].ifsc)
                startActivity(intent)
            }
        })
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false) as RecyclerView.LayoutManager
    }
}