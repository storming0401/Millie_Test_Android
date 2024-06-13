package com.kwj.common.log

import android.util.Log

object MillieLogger {

    private val TAG = "[Millie_Log]"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun e(msg: String) {
        Log.e(TAG, msg)
    }

    fun e(tag: String, msg: String, throwable: Throwable?) {
        Log.e(tag, msg, throwable)
    }

    fun i(msg: String) {
        Log.i(TAG, msg)
    }

    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    fun w(msg: String) {
        Log.w(TAG, msg)
    }

    fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }
}