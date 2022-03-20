package com.example.bankingapp.model

data class SpinnerCustomer(val name:String,val phone:String)

object SpinCustomers{
    private val names = arrayListOf<String>(
        "Shyam Singh",
        "Atharva Mittal",
        "Hridyansh Shah",
        "Akshay Agarwal",
        "Rajesh Sharma",
        "Shubh Mittal",
        "Sparsh Bansal",
        "Rajat Garg",
        "Ksheetiz Agrahari",
        "Tushar Srivastava"
    )
    private val phoneNumbers = arrayListOf<String>(
        "1234567809",
        "1789012345",
        "2678901234",
        "3890123456",
        "5012345678",
        "6392579668",
        "6567890123",
        "6901234567",
        "7345678901",
        "8456789012"
    )
    var list : ArrayList<SpinnerCustomer>? = null
        get(){
            if(field != null){
                return field
            }
            field = ArrayList()
            for(i in phoneNumbers.indices){
                val nameID = names[i]
                val phoneID = phoneNumbers[i]
                val customer = SpinnerCustomer(nameID,phoneID)
                field!!.add(customer)
            }
            return field
        }
}