package com.example.robertduriancik.weatherapp.domain.mappers

import com.example.robertduriancik.weatherapp.data.Forecast
import com.example.robertduriancik.weatherapp.data.ForecastResult
import com.example.robertduriancik.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.robertduriancik.weatherapp.domain.model.Forecast as ModelForecast

/**
 * Created by robert-ntb on 4/21/17.
 */

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<com.example.robertduriancik.weatherapp.domain.model.Forecast> {
        return list.mapIndexed { index, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): com.example.robertduriancik.weatherapp.domain.model.Forecast {
        return com.example.robertduriancik.weatherapp.domain.model.Forecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt())
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}