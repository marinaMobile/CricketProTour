package com.mobirix.d.bbr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.mobirix.d.Constants
import com.mobirix.d.MainActivity
import com.mobirix.d.R
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*

class Invite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)
        val txtEr: TextView = findViewById(R.id.txtMainMain)

        runBlocking {

            val job: Job = GlobalScope.launch(Dispatchers.IO) {
                getAsync(applicationContext)
            }
            job.join()
            val jsoup: String? = Hawk.get(Constants.asyncResult, "")
            Log.d("cora", "cora $jsoup")

            txtEr.text = jsoup

            if (jsoup == "8Nmx") {
                Intent(applicationContext, MainActivity::class.java).also { startActivity(it) }
            } else {
                Intent(applicationContext, BBBRAct::class.java).also { startActivity(it) }
            }
            finish()
        }
    }

    private suspend fun getAsync(context: Context) {
        val asyncKey = Soup(context)
        val asyncResult = asyncKey.getDocSecretKey()
        Hawk.put(Constants.asyncResult, asyncResult)
    }
}