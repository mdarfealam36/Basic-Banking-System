package com.mdarfealam.tsfbankingsystem.Adapter

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.getSystemService
import com.mdarfealam.tsfbankingsystem.Database.DbHelper
import com.mdarfealam.tsfbankingsystem.ModelClass.TransactionModel
import com.mdarfealam.tsfbankingsystem.R


class TransactionListAdapter(private val context: Activity, val arrayList: ArrayList<TransactionModel>) : BaseAdapter() {


    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent : ViewGroup?): View {
        val inflator : LayoutInflater? = context.getSystemService() as LayoutInflater?
        val rowView = inflator!!.inflate(R.layout.transaction_history_structure, parent, false)
        val t1_sender : TextView = rowView.findViewById(R.id.sender) as TextView
        val t2_receiver : TextView = rowView.findViewById(R.id.receiver)as TextView
        val t3_amount : TextView = rowView.findViewById(R.id.Amount)as TextView

        var Transactions : TransactionModel = arrayList.get(position)
        t1_sender.text = Transactions.sender.toString()
        t2_receiver.text = Transactions.receiver.toString()
        t3_amount.text = Transactions.amount.toString()
        return rowView
    }
}