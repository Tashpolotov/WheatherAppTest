package com.example.wheatherapptest.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.wheatherapptest.domain.model.weather.WeatherModel
import com.example.wheatherapptest.domain.usecase.WeatherUseCase
import com.example.wheatherapptest.utils.base.BaseViewModel
import com.example.wheatherapptest.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val useCase: WeatherUseCase):BaseViewModel() {

    private val _weather = MutableStateFlow<Resource<WeatherModel>>(Resource.Empty())
    val weather = _weather.asStateFlow()

    fun loadWeather(country:String){
        viewModelScope.launch {
            useCase.getWeather(country).collectData(_weather)
        }
    }

}