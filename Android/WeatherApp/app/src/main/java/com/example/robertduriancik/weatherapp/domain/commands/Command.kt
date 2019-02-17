package com.example.robertduriancik.weatherapp.domain.commands

/**
 * Created by robert-ntb on 4/21/17.
 */
public interface Command<T> {
    fun execute(): T
}