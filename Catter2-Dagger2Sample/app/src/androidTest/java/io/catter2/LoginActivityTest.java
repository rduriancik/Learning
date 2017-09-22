package io.catter2;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import io.catter2.login.LoginUseCase;
import io.catter2.login_activity.LoginActivity;
import io.catter2.login_activity.LoginActivityModule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by robert on 21.9.2017.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule =
            new IntentsTestRule<>(LoginActivity.class, true, false);

    @Test
    public void testSuccessfulLogIn() {
        LoginUseCase mock = Mockito.mock(LoginUseCase.class);
        Mockito.when(mock.login("user", "pass")).thenReturn("token");
        LoginActivityModule.testLoginUseCase = mock;

        intentsTestRule.launchActivity(new Intent());

        onView(withId(R.id.login_username_actv)).perform(clearText(), typeText("user"));
        onView(withId(R.id.login_password_et)).perform(clearText(), typeText("pass"), closeSoftKeyboard());
        onView(withId(R.id.login_sign_in_bt)).perform(click());

        intended(allOf(hasComponent(hasShortClassName(".favorites_activity.FavoritesActivity")),
                toPackage("io.catter2")));
        Mockito.verify(mock, Mockito.times(1)).login("user", "pass");
    }

    @Test
    public void testUnsuccessfulLogIn() {
        LoginUseCase mock = Mockito.mock(LoginUseCase.class);
        Mockito.when(mock.login(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        LoginActivityModule.testLoginUseCase = mock;

        intentsTestRule.launchActivity(new Intent());

        onView(withId(R.id.login_sign_in_bt)).perform(click());
        onView(withId(R.id.login_error_tv)).check(matches(isDisplayed()));
        Mockito.verify(mock, Mockito.times(1)).login(Mockito.anyString(), Mockito.anyString());
    }
}
