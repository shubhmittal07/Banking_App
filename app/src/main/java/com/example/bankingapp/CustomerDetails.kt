package com.example.bankingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bankingapp.databinding.ActivityCustomerDetailsBinding

class CustomerDetails : AppCompatActivity() {
    private lateinit var binding:ActivityCustomerDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val bundle : Bundle?= intent.extras
        val name = bundle!!.getString("name")
        val phone_no = bundle.getLong("phone")
        val balance = bundle.getDouble("balance").toString()
        val email = bundle.getString("email")
        val acc_No = bundle.getString("accNo")
        val ifsc = bundle.getString("ifsc")
        binding.name.text = name
        binding.phoneNo.text = phone_no.toString()
        binding.balance.text = balance.toString()
        binding.email.text = email
        binding.AccNo.text = acc_No
        binding.ifscCode.text = ifsc
        binding.transferMoney.setOnClickListener{

        }
    }
}