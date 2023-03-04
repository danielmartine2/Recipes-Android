package com.danielmartinez.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.danielmartinez.recipes.domain.Recipe
import com.danielmartinez.recipes.framework.ui.main.MainViewModel
import com.danielmartinez.recipes.usecases.GetRecipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var getRecipes: GetRecipes

    @Mock
    lateinit var observer: Observer<List<Recipe>>

    @get: Rule
    val rule = InstantTaskExecutorRule()


    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun onCreateLoadsPopularRecipes() = runBlockingTest {

        val fakeList = mutableListOf<Recipe>(Recipe(
            "Pastas",
            null,
            "",
            "",
            "",
            "",
            arrayListOf(),
            arrayListOf()
        ))
        whenever(getRecipes.invoke()).thenReturn(fakeList)
        val vm = MainViewModel(getRecipes)
        vm.recipes.observeForever(observer)

        vm.onCreate()

        verify(observer).onChanged(fakeList)
    }
}