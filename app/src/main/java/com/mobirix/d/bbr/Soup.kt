package com.mobirix.d.bbr

import android.content.Context
import com.mobirix.d.Constants
import com.mobirix.d.Constants.C1
import com.mobirix.d.Constants.DL1
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class Soup (val context: Context) {
    private var jsoup: String? = "null"
    private val hawk : String? = Hawk.get(C1)
    private val hawkAppLink: String? = Hawk.get(DL1)

    private var forJsoupSetNaming: String = Constants.lru + Constants.odone + hawk
    private var forJsoupSetAppLnk: String = Constants.lru + Constants.odone + hawkAppLink
    suspend fun getDocSecretKey(): String?{
        withContext(Dispatchers.IO){
            if(hawk!=null) {
                val doc = Jsoup.connect(forJsoupSetNaming).get()
                jsoup = doc.text().toString()
            } else {
                val doc = Jsoup.connect(forJsoupSetAppLnk).get()
                jsoup = doc.text().toString()
            }
        }
        return jsoup
    }
}