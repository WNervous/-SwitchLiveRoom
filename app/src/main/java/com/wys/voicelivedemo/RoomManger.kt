package com.wys.voicelivedemo

import com.wys.voicelivedemo.data.Room

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object RoomManger {
    var room: Room? = null

    fun isSameRoom(room1: Room): Boolean {
        return room1.title == room?.title
    }
}