package com.example.moviesrating.di.modules

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LoggingInterceptor : Interceptor {

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val response = chain.proceed(request)

            val bodyString = response.body!!.string()

            if (!response.isSuccessful) throw Exception(bodyString)

            return response.newBuilder()
                .body(bodyString.toResponseBody(response.body?.contentType()))
                .build()

        } catch (e: Exception) {
            e.printStackTrace()
            var msg = ""
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
                // TODO узнать зачем надо без него падает и объединить с другим
                .code(999)
                .body("{${e}}".toResponseBody(null))
                .build()
        }
    }

    fun <T> retrofitErrorHandler(res: retrofit2.Response<T>): T {
        if (res.isSuccessful) {
            return res.body()!!
        } else {
            val errMsg = res.errorBody()?.string()?.let {
                JSONObject(it).getString("error")
            } ?: run {
                res.code().toString()
            }

            throw Exception(errMsg)
        }
    }
}