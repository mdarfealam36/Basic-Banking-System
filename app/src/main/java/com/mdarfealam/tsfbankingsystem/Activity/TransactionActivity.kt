package com.mdarfealam.tsfbankingsystem.Activity

import android.database.Cursor
import android.widget.SimpleCursorAdapter
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mdarfealam.tsfbankingsystem.Adapter.TransactionListAdapter
import com.mdarfealam.tsfbankingsystem.Database.DbHelper
import com.mdarfealam.tsfbankingsystem.ModelClass.TransactionModel
import com.mdarfealam.tsfbankingsystem.databinding.ActivityTransactionBinding

class TransactionActivity : AppCompatActivity() {

    lateinit var binding: ActivityTransactionBinding
    lateinit var db : SQLiteDatabase
    lateinit var curser : Cursor
    lateinit var helper: DbHelper
    lateinit var arrayList : ArrayList<TransactionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        helper = DbHelper(applicationContext)
        db = helper.readableDatabase
        curser = db.rawQuery("SELECT * FROM TRANSACT",null)


        loadDataInlistView()
    }

    private fun loadDataInlistView() {
        arrayList = helper.getTransData()
        val list_adapter = TransactionListAdapter(this,arrayList)
        binding.List.adapter = list_adapter
        arrayList.reverse() // It's Make the ListView Reverse Order
        list_adapter.notifyDataSetChanged()
    }
}