package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUp_activity_btnSignUp.setOnClickListener {

            if (edtPassword.text.toString().equals(edtConfirmPass.text.toString())) {

                val signUpURL = "http://10.1.3.228/OnlineStoreApp/join_new_user.php?email=" +
                        edtEmail.text.toString() +
                        "&name=" + edtUserName.text.toString() +
                        "&pass=" + edtPassword.text.toString()

                val requestQ = Volley.newRequestQueue(this@SignUpActivity)

                val stringRequest =
                    StringRequest(Request.Method.GET, signUpURL, Response.Listener { response ->
                        if (response.equals("A user with this email Address already exist.")) {
                            alertDialog(response)
                        } else {
                            Person.email = edtEmail.text.toString()
                            alertDialog(response)
                            val homeIntent = Intent(this@SignUpActivity,HomeScreen::class.java)
                            startActivity(homeIntent)
                        }
                    }, Response.ErrorListener {

                    })
                requestQ.add(stringRequest)
            } else {
                var message = "Password Mismatch!"
                alertDialog(message)

            }

        }
        signUp_activity_btnLogin.setOnClickListener {

            finish()
        }


    }

    fun alertDialog(message: String) {

        val dialogBuilder = AlertDialog.Builder(this@SignUpActivity)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(message)
        dialogBuilder.create().show()
    }

}
