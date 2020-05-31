package me.sure.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import me.sure.mylib.IMath
import me.sure.mylib.Point
import me.sure.mylib.Tag

class MathService : Service() {

    private val mathBinder by lazy {
        object : IMath.Stub() {
            override fun pointAdd(a: Point?, b: Point?): Point {
                Log.d(Tag.me, "[pointAdd] server thread: ${Thread.currentThread()}")
                return Point().apply {
                    x = (a?.x ?: 0) + (b?.x ?: 0)
                    y = (a?.y ?: 0) + (b?.y ?: 0)
                }
            }

            override fun add(a: Int, b: Int): Int {
                Log.d(Tag.me, "[add] server thread: ${Thread.currentThread()}")
                return a + b
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(Tag.me, "MathService onBind(), $intent")
        return mathBinder
    }

    override fun onCreate() {
        Log.d(Tag.me, "MathService onCreate()")
        super.onCreate()
    }

    override fun onRebind(intent: Intent?) {
        Log.d(Tag.me, "MathService onRebind() $intent")
        super.onRebind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(Tag.me, "MathService onStartCommand() $intent, $flags, $startId")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(Tag.me, "MathService onUnbind() $intent")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(Tag.me, "MathService onDestroy()")
        super.onDestroy()
    }
}
