package com.silva.viavarejo.viewmodel

import androidx.lifecycle.Observer
import com.silva.viavarejo.base.BaseUnitTest
import com.silva.viavarejo.data.model.ProductListResponse
import com.silva.viavarejo.data.repository.ViaVarejoRepository
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class ProductListViewModelTest : BaseUnitTest() {

    lateinit var productListViewModel: ProductListViewModel

    @Mock
    lateinit var viaVarejoRepository: ViaVarejoRepository

    @Mock
    lateinit var productListResponse: ProductListResponse

    @Mock
    lateinit var observerScreenMock: Observer<ProductListViewModel.Screen>

    private var subjectDelayInnerMock = PublishSubject.create<String>()

    @Before
    fun `Setup test`() {
        MockitoAnnotations.initMocks(this)
        productListViewModel = ProductListViewModel(viaVarejoRepository)
        productListViewModel.screen.observeForever(observerScreenMock)
    }

    @Test
    fun `Test getProductList when success`() {
        `when`(viaVarejoRepository.getProductList())
            .thenReturn(Observable.just(productListResponse).delaySubscription(subjectDelayInnerMock))

        productListViewModel.getProductList()

        testProductListLoading()
        verify(viaVarejoRepository).getProductList()
        verify(observerScreenMock).onChanged(
            ProductListViewModel.Screen.SuccessProductList(
                productListResponse.products
            )
        )

    }

    @Test
    fun `Test getProductList when error`() {
        `when`(viaVarejoRepository.getProductList())
            .thenReturn(Observable.error(Exception()))

        productListViewModel.getProductList()

        verify(viaVarejoRepository).getProductList()
        verify(observerScreenMock).onChanged(
            ProductListViewModel.Screen.ShowError
        )

    }


    private fun testProductListLoading() {
        assertEquals(
            ProductListViewModel.Screen.ShowLoading,
            productListViewModel.screen.value
        )

        subjectDelayInnerMock.onComplete()

        assertEquals(
            ProductListViewModel.Screen.SuccessProductList(productListResponse.products),
            productListViewModel.screen.value
        )
    }


}