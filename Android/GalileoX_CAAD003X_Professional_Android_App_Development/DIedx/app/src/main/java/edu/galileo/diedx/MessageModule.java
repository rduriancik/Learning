package edu.galileo.diedx;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 29.7.2017.
 */
@Module
public class MessageModule {

    private Context context;

    public MessageModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Named("hi")
    @Provides
    String providesHi() {
        return "Hello!";
    }

    @Named("bye")
    @Provides
    String providesBye() {
        return "Good bye!!";
    }

    @Named("question")
    @Provides
    String provideQuestion() {
        return "What's your name???";
    }
}
