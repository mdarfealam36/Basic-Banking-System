package com.mdarfealam.tsfbankingsystem.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mdarfealam.tsfbankingsystem.ModelClass.CustomerModel
import com.mdarfealam.tsfbankingsystem.R

class CustomerRecyclerAdapter(private val context: Context, private val dataset : ArrayList<CustomerModel>):
    RecyclerView.Adapter<CustomerRecyclerAdapter.ItemViewHolder>() {

    lateinit var mListener: onItemClicklistener

    interface onItemClicklistener {
        fun onItemlick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClicklistener) {
        mListener = listener
    }

    class ItemViewHolder(private val view: View, listener: onItemClicklistener) : RecyclerView.ViewHolder(view){

        val nameView : TextView = view.findViewById(R.id.item_name)
        val type : TextView = view.findViewById(R.id.type)
        val acount_no : TextView = view.findViewById(R.id.item_acc_no)
        val image : ImageView = view.findViewById(R.id.ImageView)
        val button : Button = view.findViewById(R.id.Btn)

        init {
            button.setOnClickListener {
                listener.onItemlick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_customer_structure,parent,false)//inflate actual item view
        return ItemViewHolder(adapterLayout,mListener)//mListener for on click listener
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.nameView.setText(item.name)
        holder.type.setText(item.acc_type.toString())
        holder.acount_no.setText(item.id.toString())
        holder.image.setImageResource(R.drawable.person)
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
    
}