package com.clevertrap.basicsetupkotlin.retrofit

import android.util.Log
import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer


class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = StringBuilder()

        val request = chain.request()

        val t1 = System.nanoTime()

        builder.append("Sending request " + request.url() + " on " + "\n" + "Headers:" + "\n"
                + request.headers())
        builder.append("\n")
        builder.append("Request: " + "\n" + stringifyRequestBody(request, request.method()))
        builder.append("\n")
        builder.append("-------------------")
        builder.append("\n")

        val response = chain.proceed(request)

        val t2 = System.nanoTime()


        builder.append("Received response for " + response.request().url()
                + " in " + (((t2 - t1) / 1e6).toString() + " ms")
                + "\n"
                + "status_code: " + response.code()
                + "\n"
                + "Headers: "
                + "\n"
                + response.headers())


        val responseString = String(response.body()!!.bytes())

        builder.append(responseString)
        builder.append("\n" + "============================================================")
        builder.append("\n" + "============================================================")

        Logger.d(builder.toString())

        return response.newBuilder()
                .body(ResponseBody.create(response.body()!!.contentType(), responseString))
                .build()
    }

    private fun stringifyRequestBody(request: Request, method: String): String {
        if ("GET".equals(method, ignoreCase = true)) {
            return "No Body"
        }

        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()!!.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }

    private object Logger {

        private val LOG_ENABLE = true
        private val DETAIL_ENABLE = false

        private fun buildMsg(msg: String): String {
            val buffer = StringBuilder()

            if (DETAIL_ENABLE) {
                val stackTraceElement = Thread.currentThread().stackTrace[4]

                buffer.append("[ ")
                buffer.append(Thread.currentThread().name)
                buffer.append(": ")
                buffer.append(stackTraceElement.fileName)
                buffer.append(": ")
                buffer.append(stackTraceElement.lineNumber)
                buffer.append(": ")
                buffer.append(stackTraceElement.methodName)
                buffer.append("() ] _____ ")
            }



            buffer.append(msg)

            return buffer.toString()
        }


        fun d(msg: String) {
            if (LOG_ENABLE) {
                Log.d("LATCH", buildMsg(msg))
            }
        }
    }
}
