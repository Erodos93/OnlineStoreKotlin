package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.edtEmail
import kotlinx.android.synthetic.main.activity_main.edtPassword
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btnSignUp.setOnClickListener {
            var signIntent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(signIntent)

        }

        activity_main_btnLogIn.setOnClickListener {
            val stringLoginUrl =
                "http://10.1.8.10/OnlineStoreApp/login_app_user.php?email=" + edtEmail.text.toString() + "&pass=" + edtPassword.text.toString()
            val requestLoginQ: RequestQueue = Volley.newRequestQueue(this@MainActivity)
            val stringLogin =
                StringRequest(Request.Method.GET, stringLoginUrl, Response.Listener { response ->
                    if (response.equals("The user doesnt exist")) {
                        alertDialog(response)
//    var loginIntent=Intent(this@MainActivity,)
                    } else {
//    alertDialog(response)
                        Person.email = edtEmail.text.toString()
                        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
                        val homeIntent = Intent(this@MainActivity, HomeScreen::class.java)
                        startActivity(homeIntent)
                    }
                }, Response.ErrorListener { error ->

                    alertDialog(error.message)
                })
            requestLoginQ.add(stringLogin)

        }
    }

    fun alertDialog(message: String?) {

        val dialogBuilder = AlertDialog.Builder(this@MainActivity)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(message)
        dialogBuilder.create().show()
    }
}
