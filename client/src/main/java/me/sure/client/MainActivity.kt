package me.sure.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import me.sure.mylib.IMath
import me.sure.mylib.Point
import me.sure.mylib.Tag

class MainActivity : AppCompatActivity(), ServiceConnection {

    private var mathInterface: IMath? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        val bindServiceView = findViewById<TextView>(R.id.bind_service)
        bindServiceView.setOnClickListener {
            if (it.isActivated) {
                unbind()
            } else {
                bind()
            }
            it.isActivated = !it.isActivated
            (it as? TextView)?.text = if (it.isActivated) "Unbind" else "Bind"
            Log.d(Tag.me, "click")
        }
    }

    private fun bind() {
        val intent = Intent().apply {
            action = "me.sure.aidl.MathService"
            setPackage("me.sure.aidl")
        }
        bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    private fun unbind() {
        unbindService(this)
    }

    private fun testMath() {
        Log.d(Tag.me, "[add] client thread: ${Thread.currentThread()}")
        val res = mathInterface?.add(1, 3)
        Log.d(Tag.me, "testMath: $res")

        Thread {
            Log.d(Tag.me, "[point add] client thread: ${Thread.currentThread()}")
            val point = mathInterface?.pointAdd(
                Point().apply { x = 1; y = 2; },
                Point().apply { x = 3; y = 4 }
            )
            Log.d(Tag.me, "test point add: $point")
        }.start()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.d(Tag.me, "onServiceDisconnected: $name")
        mathInterface = null
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d(Tag.me, "onServiceConnected: $name")
        mathInterface = IMath.Stub.asInterface(service)
        testMath()
    }
}
