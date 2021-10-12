package com.example.postrequest

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
private lateinit var btnAdd: Button
private lateinit var mytValue: TextView
private lateinit var myScrollView: ScrollView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mytValue = findViewById(R.id.tvText)
        val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)

        apiInterface?.doGetListResources()?.enqueue(object : Callback<List<UserDetails.User>> {
            override fun onResponse(
                call: Call<List<UserDetails.User>>,
                response: Response<List<UserDetails.User>>
            ) {
                Log.d("TAG", response.code().toString() + "")
                var displayResponse = ""
                for (datum in response.body()!!) {
                    displayResponse =
                        displayResponse + datum.name + "\n" + datum.location + "\n" + "\n"
                }
                mytValue.text = displayResponse
            }

            override fun onFailure(call: Call<List<UserDetails.User>>, t: Throwable) {
                Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show();
            }
        })
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

}

