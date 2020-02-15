package com.silva.viavarejo.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseUnitTest {
    @get:Rule
    var rxSchedulersOverrideRule = RxImmediateSchedulerRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {
        const val STRING_TEST = "string_test"
    }
}