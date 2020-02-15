package com.silva.viavarejo.di.module

import android.app.Application
import android.content.Context
import com.silva.viavarejo.VHApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun providesAppApplication(application: Application): VHApplication {
        return application as VHApplication
    }

//    @Provides
//    @Singleton
//    internal fun providesRoomDb(app: Application): AppDatabase {
//        return (app as VHApplication).appDb
//    }
}