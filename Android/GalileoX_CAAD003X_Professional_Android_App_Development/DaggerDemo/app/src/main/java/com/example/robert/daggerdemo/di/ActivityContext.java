package com.example.robert.daggerdemo.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by robert on 23.7.2017.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
