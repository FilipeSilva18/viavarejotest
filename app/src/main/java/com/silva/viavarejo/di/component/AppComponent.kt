package com.silva.viavarejo.di.component

import android.app.Application
import com.silva.viavarejo.VHApplication
import com.silva.viavarejo.di.builder.ActivityBuilder
import com.silva.viavarejo.di.builder.ViewModelBuilder
import com.silva.viavarejo.di.module.ApiServiceModule
import com.silva.viavarejo.di.module.AppModule
import com.silva.viavarejo.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ApiServiceModule::class,
        NetworkModule::class,
        ViewModelBuilder::class,
        ActivityBuilder::class
    ]
)
interface AppComponent {

    fun inject(application: VHApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}