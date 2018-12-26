package com.example.robertduriancik.weatherapp.domain.commands

import com.example.robertduriancik.weatherapp.domain.datasource.ForecastProvider
import com.example.robertduriancik.weatherapp.domain.model.ForecastList

/**
 * Created by robert-ntb on 4/22/17.
 */

class RequestForecastCommand(val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}