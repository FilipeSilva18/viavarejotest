package com.silva.viavarejo.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.silva.viavarejo.BuildConfig
import com.silva.viavarejo.di.annotation.RetrofitApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        const val ACCEPT = "Accept"
        const val ACCEPT_CHARSET = "Accept-Charset"
        const val CONTENT_TYPE = "Content-Type"
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.API_URL
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @RetrofitApi
    fun provideRetrofitToken(gson: Gson, @Named("baseUrl") baseUrl: String): Retrofit {
        return provideRetrofit(gson, baseUrl)
    }

    private fun provideRetrofit(gson: Gson, baseUrl: String): Retrofit {
        val mLogging = HttpLoggingInterceptor()
        mLogging.level = HttpLoggingInterceptor.Level.BODY


        val builder = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .addInterceptor(getRequestInterceptor())
            .addInterceptor(mLogging)

        val okHttpClient = builder.build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    private fun getRequestInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            setFixedHeaders(requestBuilder)

            chain.proceed(requestBuilder.build())
        }
    }

    private fun setFixedHeaders(requestBuilder: Request.Builder) {
        requestBuilder.addHeader(CONTENT_TYPE, "application/json")
        requestBuilder.addHeader(ACCEPT, "application/json")
        requestBuilder.addHeader(ACCEPT_CHARSET, "UTF-8")
    }


}