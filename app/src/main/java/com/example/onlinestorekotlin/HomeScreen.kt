package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)


        var brandUrl = "http://10.1.3.228/OnlineStoreApp/fetch_brands.php"
        var brandList = ArrayList<String>()
        var requestBrand = Volley.newRequestQueue(this@HomeScreen)
        var arrayBrandRequest =
            JsonArrayRequest(Request.Method.GET, brandUrl, null, Response.Listener { response ->
                for (jsonObject in 0.until(response.length())) {
                    brandList.add(response.getJSONObject(jsonObject).getString("brand"))
                }
                var arrayBrandAdapter = ArrayAdapter(this@HomeScreen,R.layout.activity_brand_item_text_view, brandList
                )
                brandListView.adapter = arrayBrandAdapter
            }, Response.ErrorListener { error ->
                alertDialog(error.message)
            })
        requestBrand.add(arrayBrandRequest)
        brandListView.setOnItemClickListener { parent, view, position, id ->

            val selectedBrand = brandList.get(position)
            val intent = Intent(this@HomeScreen,FetchEProduct::class.java)
            intent.putExtra("BRAND",selectedBrand)
            startActivity(intent)
        }

    }

    fun alertDialog(message: String?) {

        val dialogBuilder = AlertDialog.Builder(this@HomeScreen)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(message)
        dialogBuilder.create().show()
    }
}
