package com.wys.voicelivedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.wys.voicelivedemo.adapter.RoomAdapter
import com.wys.voicelivedemo.data.Room
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
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = RoomAdapter(list)
    }
}