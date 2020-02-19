package com.example.onlinestorekotlin


import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class AmountFragment : android.app.DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)

        var edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnInsertAmount = fragmentView.findViewById<ImageButton>(R.id.btnDialogFragment)
        btnInsertAmount.setOnClickListener {

            var amountOrderUrl = "http://10.1.3.228/OnlineStoreApp/insert_temporary_order.php?" +
                    "email=${Person.email}" +
                    "&product_id=${Person.addToCartId}" +
                    "&amount=${edtEnterAmount.text.toString()}"
            var requestQ = Volley.newRequestQueue(activity)
            var stringRequest =
                StringRequest(Request.Method.GET, amountOrderUrl, Response.Listener { response ->

                    var intent = Intent(activity, CartProductActivity::class.java)
                    startActivity(intent)

                }, Response.ErrorListener { error ->

                })
            requestQ.add(stringRequest)

        }
        return fragmentView
    }


}
