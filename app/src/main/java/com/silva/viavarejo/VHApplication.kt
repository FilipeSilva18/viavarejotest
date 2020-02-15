package com.silva.viavarejo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.silva.viavarejo.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class VHApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks,
    HasActivityInjector {

    companion object {
        private lateinit var vhApplication: VHApplication
        fun getAppContext(): Context = vhApplication
    }

    @Inject
    internal lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        vhApplication = this
        initInjector()
    }

    private fun initInjector() {
        DaggerAppComponent.builder()
            .application(vhApplication)
            .build()
            .inject(vhApplication)
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityDestroyed(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
    }

    override fun onActivityResumed(p0: Activity) {
    }
}