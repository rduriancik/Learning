package edu.galileo.diedx;

import dagger.Component;

/**
 * Created by robert on 29.7.2017.
 */
@Component(modules = {MessageModule.class})
public interface MessageComponent {
    void inject(MainActivity mainActivity);
}
