package com.wys.voicelivedemo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wys.voicelivedemo.adapter.RoomChatAdapter
import com.wys.voicelivedemo.data.Room
import kotlinx.android.synthetic.main.fragment_room.bgImg
import kotlinx.android.synthetic.main.fragment_room.chatRecycleView
import kotlinx.android.synthetic.main.fragment_room.roomTitle
import kotlinx.android.synthetic.main.fragment_room.sendMessage

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class RoomFragment : Fragment() {
    private lateinit var chatAdapter: RoomChatAdapter
    private var room: Room? = null

    companion object {
        const val KEY_ROOM = "key_room"
        fun newInstance(room: Room): RoomFragment {
            val args = Bundle()
            val fragment = RoomFragment()
            args.putParcelable(KEY_ROOM, room)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        room = arguments?.getParcelable(KEY_ROOM)
        initView()
    }

    private fun initView() {
        roomTitle.text = room?.title
        bgImg.setBackgroundColor(Color.parseColor(room?.color))
        chatAdapter = RoomChatAdapter()
        chatRecycleView.layoutManager = LinearLayoutManager(context)
        chatRecycleView.adapter = chatAdapter
        sendMessage.setOnClickListener {
            chatAdapter.addMessage()
        }
    }
}