package com.week2.getir.fake_gpt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.week2.getir.fake_gpt.view.ChatFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ChatFragment())
                .commit()
        }
    }
}