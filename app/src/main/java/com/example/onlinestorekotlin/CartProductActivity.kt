package com.example.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_product.*
import java.util.ArrayList

class CartProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

        val cartUrl =
            "http://10.1.8.10/OnlineStoreApp/fench_teporary_order.php?email=${Person.email}"
        var cartarrayList = ArrayList<TemporaryCart>()
        var requestCart = Volley.newRequestQueue(this@CartProductActivity)
        var cartRequest =
            JsonArrayRequest(Request.Method.GET, cartUrl, null, Response.Listener { response ->
                for (jsonIndex in 0.until(response.length())) {
                    var jsonArrayList = response.getJSONObject(jsonIndex)

                    cartarrayList.add(
                        TemporaryCart(jsonArrayList.getInt("id"),
                            jsonArrayList.getString("name"),
                            jsonArrayList.getInt("price"),
                            jsonArrayList.getString("email"),
                            jsonArrayList.getInt("amount"))

                    )
                }
            }, Response.ErrorListener {

            })
        requestCart.add(cartRequest)
        var adapter = ArrayAdapter(
            this@CartProductActivity,
            android.R.layout.simple_list_item_1, cartarrayList

        )
        listView.adapter = adapter

    }
}
