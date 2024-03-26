package com.example.wheatherapptest

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.wheatherapptest.domain.model.weather.*
import com.example.wheatherapptest.domain.usecase.WeatherUseCase
import com.example.wheatherapptest.presentation.ui.viewmodel.WeatherViewModel
import com.example.wheatherapptest.utils.resource.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val weatherUseCase = mock(WeatherUseCase::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        ArchTaskExecutor.getInstance().setDelegate(null)
        kotlinx.coroutines.Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should loadWeather`() {
        runBlocking {
            val weather = WeatherModel(
                coord = CoordModel(lat = 0.0, lon = 0.0),
                weather = listOf(Weather(1, "description", "sadas", "asd")),
                base = "base",
                main = MainModel(
                    temp = 20.0,
                    feelsLike = 19.0,
                    tempMin = 18.0,
                    tempMax = 22.0,
                    pressure = 1013,
                    humidity = 80,
                    seaLevel = 0,
                    grndLevel = 0
                ),
                visibility = 10000,
                wind = Wind(speed = 5.0, deg = 180, gust = 1.2),
                clouds = Clouds(all = 20),
                dt = 1616822400,
                sys = Sys(country = "US", sunrise = 1616793600, sunset = 1616837400, id = 3602, type = 1),
                timezone = -14400,
                id = 5128581,
                name = "New York",
                cod = 200
            )
            val weatherFlow = MutableStateFlow(Resource.Success(weather))
            `when`(weatherUseCase.getWeather("New York")).thenReturn(weatherFlow)
            val viewModel = WeatherViewModel(weatherUseCase)

            // When
            viewModel.loadWeather("New York")

            // Then
            val result = viewModel.weather.value
            assert(result is Resource.Success)
            assert((result as Resource.Success).data == weather)
            verify(weatherUseCase).getWeather("New York")
        }
    }
}