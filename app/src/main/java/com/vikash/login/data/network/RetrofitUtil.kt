/*
 * Name: RetrofitUtil.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022. All rights reserved.
 */

package com.vikash.login.data.network

import com.google.gson.GsonBuilder
import com.vikash.login.data.RetrofitApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Modifier
import java.util.Properties
import java.util.concurrent.TimeUnit

object RetrofitUtil {
    private const val TAG = "RetrofitUtil"
    private const val TIMEOUT = 10000L

    private var prop: Properties = Properties()
    private val loggingInterceptor by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }



    private fun getBaseHttpClient(timeout: Long = TIMEOUT): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(timeout, TimeUnit.SECONDS)
        httpClient.connectTimeout(timeout, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(false)
        httpClient.addInterceptor(loggingInterceptor)
        return httpClient.build()
    }

    private fun getRetrofit(
        url: String, isScalar: Boolean,
        timeout: Long = TIMEOUT,
    ): Retrofit {
        val httpClient = getBaseHttpClient(timeout)

        val converterFactory = if (isScalar) {
            ScalarsConverterFactory.create()
        } else {
            GsonConverterFactory.create(
                GsonBuilder().excludeFieldsWithModifiers(
                    Modifier.FINAL,
                    Modifier.TRANSIENT,
                    Modifier.STATIC
                ).create()
            )
        }
        return Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .build()

    }


    fun createRetrofitApi(endpointURL: String): RetrofitApi {
        val retrofit = getRetrofit(endpointURL, false, TIMEOUT)
        return retrofit.create(RetrofitApi::class.java)
    }


//
//    private val moshi by lazy {
//        val moshiBuilder = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//        moshiBuilder.build()
//    }


}
