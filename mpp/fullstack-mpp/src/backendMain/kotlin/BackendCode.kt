package io.ktor.samples.fullstack.backend

import com.github.takahirom.sample.proto.MyProtoSample
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.ContentType
import io.ktor.http.content.*
import io.ktor.http.headersOf
import io.ktor.http.parseAndSortContentTypeHeader
import io.ktor.response.defaultTextContentType
import io.ktor.response.respondBytes
import io.ktor.routing.*
import io.ktor.samples.fullstack.common.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import java.io.*

fun Application.main() {
    val currentDir = File(".").absoluteFile
    environment.log.info("Current directory: $currentDir")

    routing {
        get("/") {
            call.respondBytes(contentType = ContentType.parse("application/protobuf")) {
                MyProtoSample
                    .ADAPTER
                    .encode(MyProtoSample(10L, "native", listOf("a", "b")))
            }
        }
        static("/static") {
            resource("ktor-samples-fullstack-mpp-frontend.js")
        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080) { main() }.start(wait = true)
}