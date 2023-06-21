package com.mdarfealam.tsfbankingsystem.Activity

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import com.mdarfealam.tsfbankingsystem.Database.DbHelper
import com.mdarfealam.tsfbankingsystem.R
import com.mdarfealam.tsfbankingsystem.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    lateinit var receiver : String
    lateinit var db : SQLiteDatabase
    lateinit var curser_new : Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data_id = intent.extras?.getString("id").toString()
        val data_name = intent.extras?.getString("name")
        val data_acc_type = intent.extras?.getString("Acc_type")
        val data_balance = intent.extras?.getString("balance").toString()
        val data_address = intent.extras?.getString("Address")

        binding.text1.text = data_id
        binding.text2.text = data_name
        binding.text3.text = data_acc_type
        binding.text4.text = data_balance
        binding.text5.text = data_address
        binding.sender.text = data_name

        val list = getItemsList()
        val adapter = ArrayAdapter<String>(this@DetailsActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@DetailsActivity, "you Selected ${adapterView?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_SHORT).show()
                receiver = adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.Btn1.setOnClickListener {
            binding.layoutLinear.visibility = View.VISIBLE
        }

        var helper = DbHelper(this@DetailsActivity)
        db = helper.readableDatabase
        curser_new = db.rawQuery("SELECT * FROM TRANSACT",null)

        binding.Btn2.setOnClickListener {
            //insert Record
            var cv_new = ContentValues()
            cv_new.put("SENDER", binding.text2.text.toString())
            cv_new.put("RECEIVER",receiver.toString())
            cv_new.put("AMOUNT",binding.amount.text.toString())

            binding.amount.setText("")

            db.insert("TRANSACT", null, cv_new)
            curser_new.requery()
            Toast.makeText(this, "Transaction completed with $receiver with amount ${binding.amount.text.toString()}", Toast.LENGTH_SHORT).show()

        }

        binding.listView.setOnClickListener {
            val intent = Intent(this,TransactionActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getItemsList(): ArrayList<String> {

        //creating the instance of DatabaseHandler class
        val databaseHandler: DbHelper = DbHelper(this)

        //calling the viewEmployee method of DatabaseHandler class to read the records
        val datalist: ArrayList<String> = databaseHandler.nameDataList()
        return datalist
    }


}