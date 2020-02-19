package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_product.*
import java.util.ArrayList

class CartProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

        var cartProductUrl =
            "http://10.1.8.10/OnlineStoreApp/fench_temporary_order.php?email=${Person.email}"
        var cartProductList = ArrayList<String>()
        var requestCart = Volley.newRequestQueue(this@CartProductActivity)
        var cartRequest =
            JsonArrayRequest(
                Request.Method.GET,
                cartProductUrl,
                null,
                Response.Listener { response ->
                    for (jsonIndex in 0.until(response.length())) {
                        var jsonArrayList = response.getJSONObject(jsonIndex)
                        cartProductList.add(
                            "${response.getJSONObject(jsonIndex).getInt("id")}\n" +
                                    "${response.getJSONObject(jsonIndex).getString("name")}\n" +
                                    "${response.getJSONObject(jsonIndex).getInt("price")}\n" +
                                    "${response.getJSONObject(jsonIndex).getString("email")}\n" +
                                    "${response.getJSONObject(jsonIndex).getInt("amount")}"
                        )


                    }
                    var cartProductAdapter = ArrayAdapter(
                        this@CartProductActivity,
                        android.R.layout.simple_list_item_1, cartProductList

                    )
                    listView.adapter = cartProductAdapter
                }, Response.ErrorListener { error ->

                })
        requestCart.add(cartRequest)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.continueShopingItem) {
            var intent = Intent(this@CartProductActivity, HomeScreen::class.java)
            startActivity(intent)
        } else if (item?.itemId == R.id.decline) {
            var deleteUrl =
                "http://10.1.8.10/OnlineStoreApp/delete_data_from_cart.php?email=${Person.email}"
            var requestQ = Volley.newRequestQueue(this@CartProductActivity)
            var stringRequest = StringRequest(Request.Method.GET, deleteUrl, Response.Listener {
                var intent = Intent(this@CartProductActivity, HomeScreen::class.java)
                startActivity(intent)
            }, Response.ErrorListener {

            })
            requestQ.add(stringRequest)
        } else if (item?.itemId == R.id.verify) {
            var verifyUrl = "http://10.1.3.228/OnlineStoreApp/verify_order.php?email=${Person.email}"
            var requestQ = Volley.newRequestQueue(this@CartProductActivity)
            var stringRequest =
                StringRequest(Request.Method.GET, verifyUrl, Response.Listener { response ->
                    var intent =
                        Intent(this@CartProductActivity, FinalizeShoppingActivity::class.java)
                    Toast.makeText(
                        this@CartProductActivity,
                        "Verify completed succes " + response,
                        Toast.LENGTH_SHORT
                    ).show()
                    intent.putExtra("LATEST_INVOICE_NUM", response)
                    startActivity(intent)
                }, Response.ErrorListener { error ->

                })
            requestQ.add(stringRequest)
        }
        return super.onOptionsItemSelected(item)
    }
}
