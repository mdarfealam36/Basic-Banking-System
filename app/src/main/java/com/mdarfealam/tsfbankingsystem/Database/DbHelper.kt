package com.mdarfealam.tsfbankingsystem.Database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.COLUMN_ACC_TYPE
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.COLUMN_ADDRESS
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.COLUMN_AMOUNT
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.COLUMN_INI_BALANCE
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.COLUMN_NAME
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.COLUMN_RECEIVER
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.COLUMN_SENDER
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.KEY_ROW_ID
import com.mdarfealam.tsfbankingsystem.Database.DbHelper.FeedEntry.KEY_TRANS_ID
import com.mdarfealam.tsfbankingsystem.ModelClass.CustomerModel
import com.mdarfealam.tsfbankingsystem.ModelClass.TransactionModel


class DbHelper(context: Context) : SQLiteOpenHelper(context,"CUSTOMER",null,2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CUS_INFO ($KEY_ROW_ID integer primary key autoincrement , $COLUMN_NAME TEXT , $COLUMN_INI_BALANCE INT , $COLUMN_ACC_TYPE TEXT ,$COLUMN_ADDRESS TEXT)")
        db?.execSQL("CREATE TABLE TRANSACT($KEY_TRANS_ID integer primary key autoincrement , $COLUMN_SENDER TEXT , $COLUMN_RECEIVER TEXT , $COLUMN_AMOUNT INTEGER) ")

        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Md Arfe Alam',10000,'SAVING','Madhubani')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Kasif Raza',11000,'CURRENT','Delhi')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Mozammil Sajid ',13000,'REGULAR','Madhubani')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Mohammad Raja',14000,'SAVING','Siwan')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Md Azhar',15000,'CURRENT','Mumbai')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Raj Kumar',16000,'REGULAR','Madhubani')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Abhishek Rawat',12000,'SAVING','Darbhanga')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Avinash Pandit',17000,'CURRENT','Kolkata')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Aditya Pandey',1800,'SAVING','Jaipur')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Sahil Kuware',15000,'CURRENT','Rajkot')")
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS TRANSACT ")
        db?.execSQL("DROP TABLE IF EXISTS CUS_INFO ")
        onCreate(db)
    }
    object FeedEntry : BaseColumns {
        const val KEY_ROW_ID = "_id"
        const val COLUMN_NAME= "NAME"
        const val COLUMN_INI_BALANCE = "BALANCE"
        const val COLUMN_ACC_TYPE = "TYPE"
        const val COLUMN_ADDRESS = "ADDRESS"

        const val KEY_TRANS_ID = "ID"
        const val COLUMN_SENDER = "SENDER"
        const val COLUMN_RECEIVER = "RECEIVER"
        const val COLUMN_AMOUNT = "AMOUNT"
    }
    fun allDataList(): ArrayList<CustomerModel> {
        val datalist : ArrayList<CustomerModel> = ArrayList<CustomerModel>()
        val selectQuery = "SELECT  * FROM CUS_INFO"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var balance : Int
        var acc_type : String
        var address :String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ROW_ID))
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                balance = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INI_BALANCE))
                acc_type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACC_TYPE))
                address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))

                val emp = CustomerModel(id = id, name = name, balance = balance,acc_type = acc_type , address = address)
                datalist.add(emp)

            } while (cursor.moveToNext())
        }
        return datalist
    }
    fun nameDataList(): ArrayList<String> {
        val datalist: ArrayList<String> = ArrayList<String>()
        val selectQuery = "SELECT  * FROM CUS_INFO"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var balance : Int
        var acc_type : String
        var address :String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ROW_ID))
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                balance = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INI_BALANCE))
                acc_type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACC_TYPE))
                address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))

                val emp = CustomerModel(id = id, name = name, balance = balance,acc_type = acc_type , address = address).name.toString()
                datalist.add(emp)

            } while (cursor.moveToNext())
        }
        return datalist
    }
    fun getTransData() :ArrayList<TransactionModel>{
        var arrayList :ArrayList<TransactionModel> = ArrayList<TransactionModel>()
        val selectQuery = "SELECT  * FROM TRANSACT"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id :Int
        var sender: String
        var receiver: String
        var amount: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TRANS_ID))
                sender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENDER))
                receiver = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECEIVER))
                amount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))

                val trans = TransactionModel(id = id, sender = sender, receiver = receiver, amount = amount)
                arrayList.add(trans)

            } while (cursor.moveToNext())
        }
        return arrayList
    }
}