package com.wys.voicelivedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * <pre>
 *     @author : wys
 *     time   : 2020/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Parcelize

data class Room constructor(val color: String, val title: String) : Parcelable