package com.example.wheatherapptest.presentation.ui.fragment

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.wheatherapptest.R
import com.example.wheatherapptest.databinding.FragmentWeatgerBinding
import com.example.wheatherapptest.presentation.ui.viewmodel.WeatherViewModel
import com.example.wheatherapptest.utils.base.BaseFragment
import com.example.wheatherapptest.utils.sharefpref.SharedPref
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

@AndroidEntryPoint
class WeatgerFragment : BaseFragment(R.layout.fragment_weatger) {

    private val binding by viewBinding(FragmentWeatgerBinding::bind)
    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var lineChart: LineChart
    private val sharedPref: SharedPref by lazy { SharedPref(requireContext()) }
    override fun initialize() {

        val cityName = sharedPref.cityy ?: arguments?.getString("cityName")


        cityName?.let {
            viewModel.loadWeather(it)
            binding.tvNameCountry.text = it
        }

        binding.imgSwap.setOnClickListener {
            searchCountry(it)
        }

        val currentDate = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        val daysOfWeek = listOf(binding.tvSun, binding.tvMon, binding.tvTue, binding.tvWed, binding.tvThu, binding.tvFri, binding.tvSat)
        val colors = listOf(R.color.black, R.color.black, R.color.black, R.color.black, R.color.black, R.color.black, R.color.black)

        daysOfWeek.forEachIndexed { index, textView ->
            val color = if (index == currentDate - 1) R.color.white else colors[index]
            textView.setTextColor(ContextCompat.getColor(requireContext(), color))
        }

        val imgBack = binding.imgBack
        val imgNext = binding.imgNext
        val linearDay = binding.linearDay

        imgBack.setOnClickListener {
            linearDay.smoothScrollBy(-100, 0)
        }

        imgNext.setOnClickListener {
            // Прокручиваем контейнер вправо
            linearDay.smoothScrollBy(100, 0)
        }
    }


    override fun initSubscribers() {

        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE | dd MMMM yyyy", Locale.getDefault())
        val formattedDate = currentDate.format(formatter)
        viewModel.weather.collectUIState (
            state = {

            },
            onSuccess = {
                binding.tvWeather.text = it.weather?.lastOrNull()?.main ?: "Не определено"
                val temperatureInCelsius = it.main.temp - 273.15
                binding.tvWeatherTemperature.text = "${temperatureInCelsius.roundToInt()}°C"
                binding.tvWeatherDay.text = formattedDate

                val chartData = listOf(temperatureInCelsius.toFloat())
                val windSpeedList = mutableListOf<Float>()
                it.wind?.let { wind ->
                    windSpeedList.add(wind.speed.toFloat())
                }

                setupWeatherChart(binding.weatherChart, chartData, windSpeedList)
            }
        )
    }

    private fun setupWeatherChart(chart: LineChart, data: List<Float>, windSpeedList: List<Float>) {
        val entries = mutableListOf<Entry>()

        data.forEachIndexed { index, temperature ->
            entries.add(Entry(index.toFloat(), temperature, windSpeedList[index]))
        }

        val dataSet = LineDataSet(entries, "Temperature")
        dataSet.color = resources.getColor(R.color.black)
        dataSet.setDrawValues(true)
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = resources.getColor(R.color.white)
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.roundToInt()} °C"
            }
        }

        val lineData = LineData(dataSet)
        chart.data = lineData

        // Настраиваем оси графика
        val xAxis = chart.xAxis
        xAxis.isEnabled = false

        val yAxisRight = chart.axisRight
        yAxisRight.isEnabled = false

        val yAxisLeft = chart.axisLeft
        yAxisLeft.isEnabled = false

        chart.legend.isEnabled = false

        chart.setDrawGridBackground(false)

        chart.axisLeft.setDrawLabels(false)
        chart.axisRight.setDrawLabels(false)
        chart.xAxis.setDrawLabels(false)

        chart.invalidate()
    }

    private fun searchCountry(v:View){
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(R.menu.poppup, popup.menu)


        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            val cityName = when(menuItem.itemId) {
                R.id.city_london -> "London"
                R.id.city_tokyo -> "Tokyo"
                R.id.city_new_york -> "New York"
                R.id.selected_city -> {
                    findNavController().navigate(R.id.action_weatgerFragment_to_searchFragment)
                    popup.dismiss()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }

            viewModel.loadWeather(cityName.toString())
            binding.tvNameCountry.text = cityName.toString()
            true
        }
        popup.show()
    }

}