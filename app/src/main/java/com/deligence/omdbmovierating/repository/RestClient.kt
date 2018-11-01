package com.deligence.omdbmovierating.retrofit

import com.deligence.omdbmovierating.constants.ServerConstants

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by fidel25 on 12/06/2017.
 */

object RestClient {

    private val errorConverter: Converter<ResponseBody, ErrorMessage>
        get() {


            val builder = Retrofit.Builder()
                    .baseUrl(ServerConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
            val retrofit = builder.build()

            return retrofit.responseBodyConverter(ErrorMessage::class.java, arrayOfNulls(0))

        }

    fun <S> createService(serviceClass: Class<S>): S {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(LoggingInterceptor())
        val builder = Retrofit.Builder()
                .baseUrl(ServerConstants.BASE_URL)
                .client(httpClient.build()).addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()

        return retrofit.create(serviceClass)
    }

    fun convertError(value: ResponseBody?): ErrorMessage{
        if(value == null){
            val error = ErrorMessage()
            error.message = ServerConstants.GENERIC_ERROR_MESSAGE
            return  error
        }
        
        return RestClient.errorConverter.convert(value)
    }
}
