package com.mdarfealam.tsfbankingsystem.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mdarfealam.tsfbankingsystem.Adapter.CustomerRecyclerAdapter
import com.mdarfealam.tsfbankingsystem.Database.DbHelper
import com.mdarfealam.tsfbankingsystem.ModelClass.CustomerModel
import com.mdarfealam.tsfbankingsystem.databinding.ActivityAllCustomerBinding

class AllCustomerActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myDataset = getItemsList()
        val recyclerView = binding.recyclerView
        var adapterRc = CustomerRecyclerAdapter(this,myDataset)
        recyclerView.adapter = adapterRc

        // code for click listener
        adapterRc.setOnItemClickListener(object : CustomerRecyclerAdapter.onItemClicklistener{
            override fun onItemlick(position: Int) {
                val data = myDataset[position]
                val intent = Intent(this@AllCustomerActivity , DetailsActivity::class.java)
                intent.putExtra("id",data.id.toString())
                intent.putExtra("name",data.name)
                intent.putExtra("Acc_type",data.acc_type)
                intent.putExtra("balance",data.balance.toString())
                intent.putExtra("Address",data.address)
                startActivity(intent)// start the activity
            }
        })

    }

    private fun getItemsList(): ArrayList<CustomerModel> {

        //creating the instance of DatabaseHandler class
        val databaseHandler: DbHelper = DbHelper(this)

        //calling the viewEmployee method of DatabaseHandler class to read the records
        val datalist: ArrayList<CustomerModel> = databaseHandler.allDataList()
        return datalist
    }

}