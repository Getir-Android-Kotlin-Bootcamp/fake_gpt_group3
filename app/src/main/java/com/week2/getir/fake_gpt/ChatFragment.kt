package com.week2.getir.fake_gpt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.week2.getir.fake_gpt.view.ChatAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class ChatFragment : Fragment() {

    private lateinit var button: ImageView
    private lateinit var etSearch: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat,container,false)
        button = view.findViewById(R.id.btnSearch)
        etSearch = view.findViewById(R.id.etSearch)
        button.setOnClickListener {
            val questions = etSearch.text.toString()
            if(checkConditions(questions)){
                // Gerekli işlemler yapılacak


            }
        }

        return view
    }

    private fun checkConditions(questions: String): Boolean {
        if(questions.toString().isNullOrEmpty()){
            return false
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvChats)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val messages = listOf(
            Message("Merhaba ben FakeGPT , nasıl yardımcı olabilirim?", false),
            Message("Siparişimde bir sorun var!", true),
            Message("Hemen kontrolleri sağlıyorum.", false),
            Message("Bekliyorum!!!!!.", true)
        )
        val adapter = ChatAdapter(messages)
        recyclerView.adapter = adapter

    }

    companion object {
        fun createSimpleIntent(context: Context): Intent =
            Intent(context, ChatFragment::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
