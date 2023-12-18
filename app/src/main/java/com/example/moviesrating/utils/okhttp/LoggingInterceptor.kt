package com.example.moviesrating.utils.okhttp

import android.content.Context
import android.os.Handler
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

const val DEFAULT_ERROR_CODE = 999

class LoggingInterceptor @Inject constructor(
    private val context: Context,
    private val handler: Handler
) : Interceptor {

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val response = chain.proceed(request)

            val bodyString = response.body!!.string()

            if (!response.isSuccessful) {
                handler.post {
                    Toast.makeText(
                        context,
                        "Error message: $bodyString + Code: ${response.code}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            return response.newBuilder()
                .body(bodyString.toResponseBody(response.body?.contentType()))
                .build()
        } catch (e: Exception) {
            val msg: String
            when (e) {
                is SocketTimeoutException -> {
                    msg = "Timeout - Please check your internet connection"
                }

                is UnknownHostException -> {
                    msg = "Unable to make a connection. Please check your internet"
                }

                is ConnectionShutdownException -> {
                    msg = "Connection shutdown. Please check your internet"
                }

                is IOException -> {
                    msg = "Server is unreachable, please try again later."
                }

                else -> {
                    msg = "$e"
                }
            }

            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message(msg)
                .code(DEFAULT_ERROR_CODE)
                .body("$e".toResponseBody(null))
                .build()
        }
    }
}
