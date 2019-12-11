package io.ktor.samples.mpp.client

import com.github.takahirom.sample.proto.MyProtoSample
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*

internal expect val ApplicationDispatcher: CoroutineDispatcher

class ApplicationApi {
    private val client = HttpClient()

    var address = Url("http://localhost:8080/")

    fun about(callback: (MyProtoSample) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: ByteArray = client.get {
                    url(this@ApplicationApi.address.toString())
                }
                val proto: MyProtoSample = MyProtoSample
                    .ADAPTER
                    .decode(result)

                callback(proto)
            }
        }
    }
}
