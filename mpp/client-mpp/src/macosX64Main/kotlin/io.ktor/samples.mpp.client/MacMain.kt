import com.github.takahirom.sample.proto.MyProtoSample
import io.ktor.http.Url
import io.ktor.samples.mpp.client.ApplicationApi
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() = runBlocking {
    val result = suspendCoroutine<MyProtoSample> { continuation ->
        ApplicationApi().about {
            continuation.resume(it)
        }
    }

    println("Result: $result")
}