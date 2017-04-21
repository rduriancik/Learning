package com.example.robertduriancik.weatherapp.domain.model

/**
 * Created by robert-ntb on 4/21/17.
 */

data class ForecastList(val city: String, val country: String,
                        val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int,
                    val low: Int)
