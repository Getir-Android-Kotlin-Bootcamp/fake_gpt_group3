package com.week2.getir.fake_gpt.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.week2.getir.fake_gpt.adapter.ChatAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import com.week2.getir.fake_gpt.BuildConfig
import com.week2.getir.fake_gpt.R
import com.week2.getir.fake_gpt.core.safetySettingList
import com.week2.getir.fake_gpt.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatFragment : Fragment() {

    private lateinit var button: ImageView
    private lateinit var etSearch: EditText
    private var isLoading = false

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvChats)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val manager = LinearLayoutManager(requireContext())
        manager.stackFromEnd = true
        recyclerView.layoutManager = manager

        val generativeModel = GenerativeModel(
            modelName = getString(R.string.model_name),
            apiKey = BuildConfig.GEMINI_KEY,
            safetySettings = safetySettingList
        )

        val messages = mutableListOf(
            Message(getString(R.string.initial_message), false),
        )

        val adapter = ChatAdapter(messages)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            val questions = etSearch.text.toString()
            if(checkConditions(questions)){
                etSearch.text.clear()
                val questionMessage = Message(questions, true)
                addMessageToDataSet(messages, questionMessage, adapter, recyclerView)
                isLoading = true
                updateAdapter(adapter, recyclerView)
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val response = generativeModel.generateContent(questions)
                        val geminiResponse = Message(response.text!!, false)

                        withContext(Dispatchers.Main) {
                            addMessageToDataSet(messages, geminiResponse, adapter, recyclerView)
                            isLoading = false
                            updateAdapter(adapter, recyclerView)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context , e.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun addMessageToDataSet(
        messageList: MutableList<Message>,
        message: Message, chatAdapter: ChatAdapter,
        recyclerView: RecyclerView
    ){
        messageList.add(message)
        recyclerView.scrollToPosition(chatAdapter.itemCount)
        chatAdapter.notifyDataSetChanged()
    }
    private fun updateAdapter(chatAdapter: ChatAdapter, recyclerView: RecyclerView) {
        if (isLoading) recyclerView.scrollToPosition(chatAdapter.itemCount)
        chatAdapter.setLoading(isLoading)
    }
    private fun checkConditions(questions: String): Boolean {
        if(questions.isNullOrEmpty()){
            return false
        }
        return true
    }

    companion object {
        fun createSimpleIntent(context: Context): Intent =
            Intent(context, ChatFragment::class.java)
    }
}
