package com.wys.voicelivedemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.wys.voicelivedemo.R
import com.wys.voicelivedemo.R.layout
import com.wys.voicelivedemo.RoomFragment
import com.wys.voicelivedemo.adapter.RoomCoverAdapter.CoverHolder
import kotlinx.android.synthetic.main.item_cover.view.roomCover

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   : 滑动直播间封面适配器
 *     version: 1.0
 * </pre>
 */
class RoomCoverAdapter(val supportFragmentManager: FragmentManager) : RecyclerView.Adapter<CoverHolder>() {

    private val colors = arrayOf(Color.BLACK, Color.BLUE, Color.GREEN)
    private var fragment: RoomFragment? = null
    private var currentPage = Int.MAX_VALUE / 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverHolder {
        return CoverHolder(LayoutInflater.from(parent.context).inflate(
            layout.item_cover, parent, false))
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: CoverHolder, position: Int) {
        holder.itemView.roomCover.setBackgroundColor(colors[position % colors.size])
        if (currentPage == position) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment!!).commitAllowingStateLoss()
            holder.itemView.roomCover.visibility = View.GONE
        } else {
            holder.itemView.roomCover.visibility = View.VISIBLE
        }
    }

    fun setFragment(fragment: RoomFragment, page: Int) {
        this.fragment = fragment
        currentPage = page
        notifyDataSetChanged()
    }

    class CoverHolder(view: View) : RecyclerView.ViewHolder(view)
}



