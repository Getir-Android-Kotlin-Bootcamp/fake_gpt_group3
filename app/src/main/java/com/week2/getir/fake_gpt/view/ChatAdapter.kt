package com.week2.getir.fake_gpt.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.week2.getir.fake_gpt.Message
import com.week2.getir.fake_gpt.R

class ChatAdapter(private val messages: List<Message>, private var isLoading: Boolean = false) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SENT = 0
        private const val TYPE_RECEIVED = 1
        private const val TYPE_LOADING = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == itemCount - 1) {
            TYPE_LOADING
        } else {
            val message = messages[position]
            if (message.isSent) TYPE_SENT else TYPE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_customer, parent, false)
                SentMessageViewHolder(view)
            }
            TYPE_RECEIVED -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_gpt, parent, false)
                ReceivedMessageViewHolder(view)
            }
            TYPE_LOADING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.custom_loading_view, parent, false)
                LoadingViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SentMessageViewHolder -> holder.bind(messages[position])
            is ReceivedMessageViewHolder -> holder.bind(messages[position])
            is LoadingViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return if (isLoading) messages.size + 1 else messages.size
    }

    inner class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) {
            itemView.findViewById<TextView>(R.id.text_message_body).text = message.body
        }
    }

    inner class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) {
            itemView.findViewById<TextView>(R.id.text_message_body).text = message.body
        }
    }
    fun setLoading(loading: Boolean) {
        if (isLoading != loading) {
            isLoading = loading
            if (loading) {
                notifyItemInserted(itemCount - 1)
            } else {
                notifyItemRemoved(itemCount)
            }
        }
    }


    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {}
    }
}