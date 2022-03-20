package com.example.bankingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.bankingapp.adapter.SpinnerArrayAdapterCustomer
import com.example.bankingapp.databinding.ActivityCustomerDetailsBinding
import com.example.bankingapp.dbHelper.DBhelper
import com.example.bankingapp.model.SpinCustomers
import com.example.bankingapp.model.SpinnerCustomer
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class CustomerDetails : AppCompatActivity() {
    private lateinit var binding:ActivityCustomerDetailsBinding
    private lateinit var dbHelper:DBhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val bundle : Bundle?= intent.extras
        val name = bundle!!.getString("name")
        val phone_no = bundle.getLong("phone").toString()
        val balance = bundle.getDouble("balance").toString()
        val email = bundle.getString("email")
        val acc_No = bundle.getString("accNo")
        val ifsc = bundle.getString("ifsc")
        val persons = resources.getStringArray(R.array.Persons)
        lateinit var transferUserPhoneNumber:String
        lateinit var transferUserName:String
        dbHelper = DBhelper(this)
        binding.name.text = name
        binding.phoneNo.text = phone_no
        binding.balance.text = balance
        binding.email.text = email
        binding.AccNo.text = acc_No
        binding.ifscCode.text = ifsc
        binding.transferMoney.setOnClickListener{
            val dialogView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,null)
            val alertDialog = AlertDialog.Builder(this)
                .setView(dialogView)
            alertDialog.setCancelable(true)
            alertDialog.show()
            val dropSpinner = dialogView.findViewById<Spinner>(R.id.spinner)
            val adapterArray = SpinnerArrayAdapterCustomer(this, SpinCustomers.list!!)
            dropSpinner.adapter = adapterArray
            val transferBtn = dialogView.findViewById<Button>(R.id.transferMoneyD)
            val amountEditTxt = dialogView.findViewById<EditText>(R.id.editAmount)
            dropSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val item:SpinnerCustomer = adapterView?.getItemAtPosition(position) as SpinnerCustomer
                    if(item.name == name){
                        amountEditTxt.isEnabled = false
                        transferBtn.isEnabled = false
                    }
                    else{
                        transferUserPhoneNumber = item.phone
                        transferUserName = item.name
                        amountEditTxt.isEnabled = true
                        transferBtn.isEnabled = true
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
            transferBtn.setOnClickListener{
                if(amountEditTxt.text.isEmpty()){
                    Toast.makeText(this@CustomerDetails,"Please enter the amount to transfer",Toast.LENGTH_LONG).show()
                }
                else{
                    val inputAmount = amountEditTxt.text.toString()
                    if((inputAmount.toDouble() <= 0) || (inputAmount.toDouble() > balance.toDouble())){
                        Toast.makeText(this@CustomerDetails,"Invalid Amount",Toast.LENGTH_LONG).show()
                    }
                    else{
                        val updatedUserAmount = ((balance.toDouble()) - (inputAmount.toDouble()))
                        val cursor = dbHelper.readParticularData(transferUserPhoneNumber)
                        if (cursor != null) {
                            if(cursor.moveToNext()){
                                //val transferUserAmount = cursor.getDouble(0)
                                //val updatedTransferUserAmount = transferUserAmount + inputAmount.toDouble()
                                //dbHelper.updateAmount(phone_no,updatedUserAmount.toString())
                                //dbHelper.updateAmount(transferUserPhoneNumber,updatedTransferUserAmount.toString())
                                val sdf = SimpleDateFormat("dd-MMM-yyyy, hh:mm:ss")
                                val currDT = sdf.format(Date()).toString()
                                //Toast.makeText(this@CustomerDetails,currDT,Toast.LENGTH_LONG).show()
                                dbHelper.insertTransferData(currDT,name,transferUserName,inputAmount,"Success")
                                Toast.makeText(this@CustomerDetails,"Transfer Successful",Toast.LENGTH_LONG).show()
                            }
                        }
                        val reload = Intent(this@CustomerDetails,CustomerDetails::class.java)
                        finish()
                        startActivity(reload)
                    }
                }
            }

        }
        binding.transactionsBtn.setOnClickListener{
            startActivity(Intent(this@CustomerDetails,TransactionsActivity::class.java))
        }
    }
}