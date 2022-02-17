package com.example.starwarsencyclopedia

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarsencyclopedia.model.CharacterViewModel
import com.example.starwarsencyclopedia.network.characterapi.Character
import com.example.starwarsencyclopedia.network.characterapi.CharacterApi
import com.example.starwarsencyclopedia.network.characterapi.CharacterApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CharacterViewModelTests : BaseTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: CharacterApiService

    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                ))
            .build()
            .create(CharacterApiService::class.java)

    }

    @Test
    fun buttons_work_as_intended() {
        viewModel = CharacterViewModel(FakeList.list)

        viewModel.pageUp()
        viewModel.pageUp()
        viewModel.pageDown()

        assertEquals(1, viewModel.currentPageNum.value!!)
        assertEquals(FakeList.list[11], viewModel.currentPage.value!![1])
    }
}