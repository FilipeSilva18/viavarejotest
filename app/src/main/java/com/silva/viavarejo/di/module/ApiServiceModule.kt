package com.silva.viavarejo.di.module

import com.silva.viavarejo.data.remote.ViaVarejoApi
import com.silva.viavarejo.di.annotation.RetrofitApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiServiceModule {

    @Provides
    internal fun provideViaVarejoApi(@RetrofitApi retrofit: Retrofit): ViaVarejoApi {
        return retrofit.create(ViaVarejoApi::class.java)
    }
}