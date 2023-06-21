package com.mdarfealam.tsfbankingsystem.ModelClass


data class TransactionModel(val id : Int? ,
                        val sender : String ,
                        val receiver : String?,
                        val amount : Int)