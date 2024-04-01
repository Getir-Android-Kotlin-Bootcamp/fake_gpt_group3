package com.week2.getir.fake_gpt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.week2.getir.fake_gpt.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding ?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(layoutInflater,container,false)
        binding.btnSearch.setOnClickListener {
            val questions = binding.etSearch.text.toString()
            if(checkConditions(questions)){
                // Gerekli işlemler yapılacak


            }
        }

        return binding.root
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
        _binding = null
    }
}