package com.wys.voicelivedemo.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class MyBroadcast(private val finish: FinishActivity?) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        this.finish?.finishAct()
    }
}

interface FinishActivity {
    fun finishAct()
}