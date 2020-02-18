package com.example.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_eproduct.*

import kotlin.collections.ArrayList

class FetchEProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproduct)

        val selectedBrand = intent.getStringExtra("BRAND")
        txtBrand.text = "Product of $selectedBrand"
        val stringBrandUrl =
            "http://10.1.8.10/OnlineStoreApp/brand_eproduct.php?brand=$selectedBrand"
        var eproductArrayList = ArrayList<EProducts>()
        val requestBrandArray = Volley.newRequestQueue(this)
        val jsonAR = JsonArrayRequest(
            Request.Method.GET,
            stringBrandUrl,
            null,
            Response.Listener { response ->
                for (productJSO in 0.until(response.length())) {
                    var jsonArrayList = response.getJSONObject(productJSO)
                    eproductArrayList.add(EProducts(jsonArrayList.getInt("id"),
                        jsonArrayList.getString("name"),
                        jsonArrayList.getInt("price"),
                        jsonArrayList.getString("brand"),
                        jsonArrayList.getString("picture")))
                }
                val pAdapter = RVEproductAdapter(this@FetchEProduct,eproductArrayList)
                recyclerView.layoutManager = LinearLayoutManager(this@FetchEProduct)
                recyclerView.adapter = pAdapter


            },
            Response.ErrorListener { error ->
                alertDialog(error.message)
            })
        requestBrandArray.add(jsonAR)
    }

    fun alertDialog(message: String?) {

        val dialogBuilder = AlertDialog.Builder(this@FetchEProduct)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(message)
        dialogBuilder.create().show()
    }
}
