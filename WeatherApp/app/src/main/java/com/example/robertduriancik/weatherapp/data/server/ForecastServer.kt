package com.example.robertduriancik.weatherapp.data.server

import com.example.robertduriancik.weatherapp.data.db.ForecastDb
import com.example.robertduriancik.weatherapp.domain.datasource.ForecastDataSource
import com.example.robertduriancik.weatherapp.domain.model.Forecast
import com.example.robertduriancik.weatherapp.domain.model.ForecastList

/**
 * Created by robert on 11.7.2017.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast?
            = throw UnsupportedOperationException()
}