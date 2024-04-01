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

class ChatFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
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
}
