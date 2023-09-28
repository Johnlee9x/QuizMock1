package com.example.quizmock1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizmock1.data.api.RetrofitBuilder
import com.example.quizmock1.data.api.UserApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val userApi = RetrofitBuilder.getInstance().create(UserApi::class.java)

        GlobalScope.launch {
            val result = userApi.getUser()
            if (result != null)
            // Checking the results
                Log.d("ayush: ", result.body().toString())
        }*/
    }
}