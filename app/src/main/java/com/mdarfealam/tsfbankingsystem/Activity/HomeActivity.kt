package com.mdarfealam.tsfbankingsystem.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mdarfealam.tsfbankingsystem.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.AllCus.setOnClickListener {
            all_customes()
        }
        binding.transactions.setOnClickListener {
            transaction()
        }

    }
    fun all_customes(){
        val intent1 = Intent(this,AllCustomerActivity::class.java)
        startActivity(intent1)
    }
    fun transaction(){
        val intent2 = Intent(this,TransactionActivity::class.java)// TransactionActivity = ListViewOfData
        startActivity(intent2)
    }

}