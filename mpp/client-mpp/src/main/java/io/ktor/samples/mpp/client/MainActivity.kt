package io.ktor.samples.mpp.client

import android.os.*
import android.support.v7.app.*
import android.widget.*
import io.ktor.http.Url
import kotlinx.coroutines.*
import kotlinx.coroutines.android.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = ApplicationApi().apply{
            address = Url("http://10.0.2.2:8080/")
        }

        api.about {
            GlobalScope.apply {
                launch(Dispatchers.Main) {
                    findViewById<TextView>(R.id.about).text = it.toString()
                }
            }
        }

        setContentView(R.layout.activity_main)
    }
}
