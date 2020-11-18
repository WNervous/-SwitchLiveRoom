package com.wys.voicelivedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wys.voicelivedemo.R
import com.wys.voicelivedemo.adapter.RoomChatAdapter.RoomChatHolder
import kotlinx.android.synthetic.main.item_chat_message.view.message

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class RoomChatAdapter : RecyclerView.Adapter<RoomChatHolder>() {

    private val mutList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomChatHolder {
        return RoomChatHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false))
    }

    override fun getItemCount(): Int = mutList.size

    override fun onBindViewHolder(holder: RoomChatHolder, position: Int) {
        holder.itemView.message.text = mutList[position]
    }

    fun addMessage() {
        mutList.add("小明：大家好!!!")
        notifyDataSetChanged()
    }

    class RoomChatHolder(view: View) : RecyclerView.ViewHolder(view)
}