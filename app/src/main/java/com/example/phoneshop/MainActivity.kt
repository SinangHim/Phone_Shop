package com.example.phoneshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager1

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PhoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerView.layoutManager = LinearLayoutManager1(this)

        adapter = PhoneAdapter()
        recyclerView.adapter = adapter

        textAll.setOnClickListener {
            val intent = Intent(this,NewActivity::class.java)
            startActivity(intent)
        }

        loadphonesFromServer()


    }

    fun loadphonesFromServer() {
        val url = "http://lp.js-cambodia.com/rupp/phones.php"
        val request = JsonArrayRequest(url, Response.Listener
        {
            val phones = arrayListOf<Phone>()
            for(i in 0 until it.length()){
                val phoneJson = it.getJSONObject(i)
                val id = phoneJson.getInt("id")
                val name = phoneJson.getString("name")
                val price = phoneJson.getInt("price")
                val brandId = phoneJson.getInt("brandId")
                val imageUrl = phoneJson.getString("imageUrl")

                val phone = Phone(id,name,price,brandId, imageUrl)
                phones.add(phone)
            } // end for
            adapter.phones = phones
            adapter.notifyDataSetChanged()
        },
            Response.ErrorListener
            {
                Toast.makeText(this, "Could not fetch data please try again..",Toast.LENGTH_LONG).show()
                Log.d("FE", "Could not Fetch data error:" + it.message)
            }) // end val request
        Volley.newRequestQueue(this).add(request)
    }
}
