package com.week2.getir.fake_gpt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.week2.getir.fake_gpt.view.ChatAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.android.material.card.MaterialCardView
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

        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyDZ_AazukuPE80_zFKfPhY6PzGGxuFG0wc",
            safetySettings = safetySettingList
        )

        val messages = mutableListOf(
            Message("Merhaba ben FakeGPT , nasıl yardımcı olabilirim?", false),
        )

        val adapter = ChatAdapter(messages)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            val questions = etSearch.text.toString()
            if(checkConditions(questions)){
                etSearch.text.clear()
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(button.windowToken, 0)
                // Gerekli işlemler yapılacak
                val questionMessage = Message(questions, true)
                addMessageToDataSet(messages, questionMessage, adapter)
                isLoading = true
                updateAdapter(adapter)
                recyclerView.smoothScrollToPosition(messages.size - 1)
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val response = generativeModel.generateContent(questions)
                        val geminiResponse = Message(response.text!!, false)

                        withContext(Dispatchers.Main) {
                            addMessageToDataSet(messages, geminiResponse, adapter)
                            isLoading = false
                            updateAdapter(adapter)
                            recyclerView.smoothScrollToPosition(messages.size - 1)
                        }
                    } catch (e: Exception) {
                        // Network error etc
                        //println("${e.message}")
                    }
                }
            }
        }
    }

    private fun addMessageToDataSet(messageList: MutableList<Message>, message: Message, chatAdapter: ChatAdapter){
        messageList.add(message)
        chatAdapter.notifyDataSetChanged()
    }
    private fun updateAdapter(chatAdapter: ChatAdapter) {
        chatAdapter.setLoading(isLoading)
    }

    companion object {
        fun createSimpleIntent(context: Context): Intent =
            Intent(context, ChatFragment::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
