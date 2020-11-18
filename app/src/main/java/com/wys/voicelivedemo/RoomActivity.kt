package com.wys.voicelivedemo

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.wys.voicelivedemo.adapter.RoomCoverAdapter
import com.wys.voicelivedemo.broadcast.FinishActivity
import com.wys.voicelivedemo.broadcast.MyBroadcast
import com.wys.voicelivedemo.data.Room
import kotlinx.android.synthetic.main.activity_room.coverRecyclerView

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class RoomActivity : AppCompatActivity() {

    lateinit var roomList: ArrayList<Room>
    lateinit var adapter: RoomCoverAdapter
    var index = 0
    private var currentCompletePage = Int.MAX_VALUE / 2
    private lateinit var myBroadcast: MyBroadcast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        roomList = intent.getParcelableArrayListExtra(KEY_ROOM)
        index = intent.getIntExtra(KEY_INDEX, 0)
        Log.d("RoomActivity", "roomlist size:${roomList.size}  index=$index")
        initView()
        register()
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(this)
        coverRecyclerView.layoutManager = linearLayoutManager
        adapter = RoomCoverAdapter(supportFragmentManager)
        coverRecyclerView.adapter = adapter
        adapter.setFragment(RoomFragment.newInstance(roomList[index]), currentCompletePage)
        PagerSnapHelper().attachToRecyclerView(coverRecyclerView)
        coverRecyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    return
                }
                val first = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                if (first == -1 || first == currentCompletePage) return //
                Log.d(TAG, "first pos =$first")
                // 找到当前的滑到位置
                currentCompletePage = first
                // 滑动的位置距离初始位置偏移
                val offSetPage = currentCompletePage - Int.MAX_VALUE / 2
                Log.e(TAG, "offSetPage=$offSetPage  currentCompletePage=$currentCompletePage ")
                // 计算应该取数据源中的下标信息
                val dataIndex = (offSetPage + index) % roomList.size
                Log.e(TAG, "dataIndex=$dataIndex ")
                val sourceIndex = if (dataIndex >= 0) {
                    dataIndex
                } else {
                    dataIndex + roomList.size
                }
                Log.e(TAG, "INDEX =$sourceIndex")
                adapter.setFragment(RoomFragment.newInstance(roomList[sourceIndex]), currentCompletePage)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        coverRecyclerView.scrollToPosition(Int.MAX_VALUE / 2)
    }

    private fun register() {
        val intent = IntentFilter()
        intent.addAction("close_room_activity")
        myBroadcast = MyBroadcast(object : FinishActivity {
            override fun finishAct() {
                Log.d(TAG, "FinishActivity")
                finish()
            }
        })
        registerReceiver(myBroadcast, intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG, "onNewIntent")
        if (intent == null) {
            return
        }
        index = intent.getIntExtra(KEY_INDEX, 0)
        // == -1 说明是相同的直播间
        if (index == -1) {
            return
        }
        roomList = intent.getParcelableArrayListExtra(KEY_ROOM)
        currentCompletePage = Int.MAX_VALUE / 2
        if (roomList.isEmpty()) {
            return
        }
        if (RoomManger.isSameRoom(room1 = roomList[index])) {
            return
        }
        adapter.setFragment(RoomFragment.newInstance(roomList[index]), currentCompletePage)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcast)
    }

    companion object {
        const val KEY_ROOM = "key_room"
        const val KEY_INDEX = "key_index"
        const val TAG = "RoomActivity"
        fun open(context: Context, room: ArrayList<Room>, index: Int) {
            Intent(context, RoomActivity::class.java).let {
                it.putParcelableArrayListExtra(KEY_ROOM, room)
                it.putExtra(KEY_INDEX, index)
                context.startActivity(it)
            }
        }

        fun open(context: Context) {
            Intent(context, RoomActivity::class.java).let {
                it.putExtra(KEY_INDEX, -1)
                context.startActivity(it)
            }
        }
    }
}