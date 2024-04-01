package com.week2.getir.fake_gpt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.week2.getir.fake_gpt.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var button: Button
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

    companion object {
        fun createSimpleIntent(context: Context): Intent =
            Intent(context, ChatFragment::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}