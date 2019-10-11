package com.example.phoneshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder.view.*

class PhoneAdapter: RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {
    var phones = arrayListOf<Phone>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.view_holder,parent,false)

        return  PhoneViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val phone = phones[position]
        holder.itemView.textName.text = phone.name
        holder.itemView.textPrice.text = "\$ ${phone.price}"
        holder.itemView.imgPhone.setImageURI(phone.imageUrl)
    }

    class PhoneViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

    }

}