package com.silva.viavarejo.repository

import com.silva.viavarejo.base.BaseUnitTest
import com.silva.viavarejo.data.model.ProductDetailResponse
import com.silva.viavarejo.data.model.ProductListResponse
import com.silva.viavarejo.data.model.WhoSawBoughtResponse
import com.silva.viavarejo.data.remote.ViaVarejoApi
import com.silva.viavarejo.data.repository.ViaVarejoRepository
import com.silva.viavarejo.viewmodel.ProductDetailViewModel
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViaVarejoRepositoryTest : BaseUnitTest() {

    lateinit var viaVarejoRepository: ViaVarejoRepository

    @Mock
    lateinit var viaVarejoApi: ViaVarejoApi

    @Mock
    lateinit var productListResponse: ProductListResponse

    @Mock
    lateinit var productDetailResponse: ProductDetailResponse

    @Mock
    lateinit var productDetailPresentation: ProductDetailViewModel.Presentation

    @Mock
    lateinit var whoSawBoughtResponse: MutableList<WhoSawBoughtResponse>

    @Before
    fun `Setup test`() {
        MockitoAnnotations.initMocks(this)
        viaVarejoRepository = ViaVarejoRepository(viaVarejoApi)
    }

    @Test
    fun `Test getProductList when success`() {
        `when`(viaVarejoApi.getProductList())
            .thenReturn(Observable.just(productListResponse))

        val testObserver = viaVarejoRepository.getProductList().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        Assert.assertEquals(productListResponse, testObserver.values()[0])
    }

    @Test
    fun `Test getWhoSawBought when success`() {
        `when`(viaVarejoApi.getWhoSawBought())
            .thenReturn(Observable.just(whoSawBoughtResponse))

        val testObserver = viaVarejoRepository.getWhoSawBought().test()
        testObserver.assertNoErrors()
        Assert.assertEquals(whoSawBoughtResponse, testObserver.values()[0])
    }

    @Test
    fun `Test getProductDetail when success`() {
        `when`(viaVarejoApi.getProductDetail())
            .thenReturn(Observable.just(productDetailResponse))
        `when`(productDetailResponse.toProductPresentation())
            .thenReturn(productDetailPresentation)

        val testObserver = viaVarejoRepository.getProductDetail().test()
        testObserver.assertNoErrors()
        Assert.assertEquals(productDetailResponse.toProductPresentation(), testObserver.values()[0])
    }


}