package com.wys.voicelivedemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.wys.voicelivedemo.adapter.RoomAdapter
import com.wys.voicelivedemo.data.Room
import kotlinx.android.synthetic.main.activity_main.floatButton
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<Room> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initData() {
        val a = resources.getStringArray(R.array.colors_array)
        println(a)
        for (i in 0..9) {
            list.add(Room(title = "直播间$i", color = "#${a[i]}"))
        }
    }

    private fun initView() {
        floatButton.visibility = View.GONE
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = RoomAdapter(list)
        floatButton.setOnClickListener {
            RoomActivity.open(this@MainActivity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sendBroadcast(Intent("close_room_activity"))
    }

    override fun onResume() {
        super.onResume()
        floatButton.let {
            it.visibility = if (RoomManger.room == null) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }
}