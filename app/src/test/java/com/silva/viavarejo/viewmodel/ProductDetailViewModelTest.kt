package com.silva.viavarejo.viewmodel

import androidx.lifecycle.Observer
import com.silva.viavarejo.base.BaseUnitTest
import com.silva.viavarejo.data.model.WhoSawBoughtResponse
import com.silva.viavarejo.data.repository.ViaVarejoRepository
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductDetailViewModelTest : BaseUnitTest(){

    lateinit var productDetailViewModel: ProductDetailViewModel

    @Mock
    lateinit var viaVarejoRepository: ViaVarejoRepository

    @Mock
    lateinit var productDetailPresentation: ProductDetailViewModel.Presentation

    @Mock
    lateinit var whoSawBoughtResponse: MutableList<WhoSawBoughtResponse>

    @Mock
    lateinit var observerScreenMock: Observer<ProductDetailViewModel.Screen>

    @Mock
    lateinit var observerPresentationMock: Observer<ProductDetailViewModel.Presentation>

    private var subjectDelayInnerMock = PublishSubject.create<String>()

    @Before
    fun `Setup test`() {
        MockitoAnnotations.initMocks(this)
        productDetailViewModel = ProductDetailViewModel(viaVarejoRepository)

        productDetailViewModel.screen.observeForever(observerScreenMock)
        productDetailViewModel.presentation.observeForever(observerPresentationMock)
    }

    @Test
    fun `Test initPresentation behavior`() {

        productDetailViewModel.initPresentation()

        Mockito.verify(observerPresentationMock).onChanged(
            ProductDetailViewModel.Presentation()
        )

    }

    @Test
    fun `Test getProductDetail when success`() {
        Mockito.`when`(viaVarejoRepository.getProductDetail())
            .thenReturn(
                Observable.just(productDetailPresentation).delaySubscription(
                    subjectDelayInnerMock
                )
            )

        productDetailViewModel.getProductDetail()

        testProductListLoading()
        Mockito.verify(viaVarejoRepository).getProductDetail()
        Mockito.verify(observerPresentationMock).onChanged(
            productDetailPresentation
        )

    }

    @Test
    fun `Test getProductDetail when error`() {
        Mockito.`when`(viaVarejoRepository.getProductDetail())
            .thenReturn(Observable.error(Exception()))

        productDetailViewModel.getProductDetail()

        Mockito.verify(viaVarejoRepository).getProductDetail()
        Mockito.verify(observerScreenMock).onChanged(
            ProductDetailViewModel.Screen.ShowError
        )

    }

    @Test
    fun `Test getWhoSawBought when success`() {
        Mockito.`when`(viaVarejoRepository.getWhoSawBought())
            .thenReturn(
                Observable.just(whoSawBoughtResponse)
            )

        productDetailViewModel.getWhoSawBought()

        Mockito.verify(viaVarejoRepository).getWhoSawBought()
        Mockito.verify(observerScreenMock).onChanged(
            ProductDetailViewModel.Screen.WhoSawBoughtSuccess(whoSawBoughtResponse)
        )

    }

    @Test
    fun `Test getWhoSawBought when error`() {
        Mockito.`when`(viaVarejoRepository.getWhoSawBought())
            .thenReturn(Observable.error(Exception()))

        productDetailViewModel.getWhoSawBought()

        Mockito.verify(viaVarejoRepository).getWhoSawBought()
        Mockito.verify(observerScreenMock).onChanged(
            ProductDetailViewModel.Screen.ShowError
        )

    }

    private fun testProductListLoading() {
        assertEquals(
            ProductDetailViewModel.Screen.ShowLoading,
            productDetailViewModel.screen.value
        )

        subjectDelayInnerMock.onComplete()

        assertEquals(
            ProductDetailViewModel.Screen.HideLoading,
            productDetailViewModel.screen.value
        )
    }

}