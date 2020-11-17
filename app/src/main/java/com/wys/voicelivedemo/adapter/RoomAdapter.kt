package com.wys.voicelivedemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wys.voicelivedemo.R.layout
import com.wys.voicelivedemo.RoomActivity
import com.wys.voicelivedemo.adapter.RoomAdapter.RoomHolder
import com.wys.voicelivedemo.data.Room
import kotlinx.android.synthetic.main.item_room.view.roomCover
import kotlinx.android.synthetic.main.item_room.view.roomTitle

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class RoomAdapter(private val list: ArrayList<Room>) : RecyclerView.Adapter<RoomHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        return RoomHolder(LayoutInflater.from(parent.context).inflate(
            layout.item_room, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        holder.itemView.roomCover.setBackgroundColor(Color.parseColor(list[position].color))
        holder.itemView.roomTitle.text = list[position].title
        holder.itemView.setOnClickListener {
            RoomActivity.open(context = holder.itemView.context, room = this.list, index = position)
        }
    }

    class RoomHolder(view: View) : RecyclerView.ViewHolder(view)
}



