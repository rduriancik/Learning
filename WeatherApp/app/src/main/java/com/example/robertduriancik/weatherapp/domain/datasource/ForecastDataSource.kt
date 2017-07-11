package com.example.robertduriancik.weatherapp.domain.datasource

import com.example.robertduriancik.weatherapp.domain.model.ForecastList

/**
 * Created by robert on 11.7.2017.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}