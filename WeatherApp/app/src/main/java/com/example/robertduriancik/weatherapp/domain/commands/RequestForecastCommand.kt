package com.example.robertduriancik.weatherapp.domain.commands

import com.example.robertduriancik.weatherapp.data.ForecastRequest
import com.example.robertduriancik.weatherapp.domain.mappers.ForecastDataMapper
import com.example.robertduriancik.weatherapp.domain.model.ForecastList

/**
 * Created by robert-ntb on 4/22/17.
 */

class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}