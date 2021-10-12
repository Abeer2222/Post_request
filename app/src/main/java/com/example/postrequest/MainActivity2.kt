package com.example.postrequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private lateinit var edName:EditText
    private lateinit var edLocations : EditText
    private lateinit var btnSave:Button
    private lateinit var btnView : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        edName=findViewById(R.id.edName)
        edLocations=findViewById(R.id.edLocations)
        btnSave=findViewById(R.id.btnSave)
        btnSave.setOnClickListener{
            var f= UserDetails.User(edName.text.toString(),edLocations.text.toString())
            addUsers(f, onResult = {
                edName.setText("")
                edLocations.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }
        btnView=findViewById(R.id.btnView)
        btnView.setOnClickListener{
            val int = Intent(this,MainActivity::class.java)
            startActivity(int)
        }
    }
    private fun addUsers(f:UserDetails.User,onResult: ()->Unit){
        val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)
        apiInterface?.addUser(f)?.enqueue(object : Callback<UserDetails.User> {
            override fun onResponse(call: Call<UserDetails.User>, response: Response<UserDetails.User>) {
                onResult()
            }

            override fun onFailure(call: Call<UserDetails.User>, t: Throwable) {
                onResult()
                Toast.makeText(this@MainActivity2,"Not Saved &&",Toast.LENGTH_LONG).show()
            }
        })
    }
}