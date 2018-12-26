package com.example.robertduriancik.weatherapp.domain.commands

import com.example.robertduriancik.weatherapp.domain.datasource.ForecastProvider
import com.example.robertduriancik.weatherapp.domain.model.Forecast

/**
 * Created by robert on 17.7.2017.
 */
class RequestDayForecastCommand(val id: Long,
                                val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute(): Forecast  = forecastProvider.requestForecast(id)
}