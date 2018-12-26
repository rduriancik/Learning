package com.example.robert.facebookrecipes;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by robert on 5.10.2017.
 */

public abstract class BaseTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        if (RuntimeEnvironment.application != null) {
            ShadowApplication shadowApp = Shadows.shadowOf(RuntimeEnvironment.application);
            shadowApp.grantPermissions("android.permission.INTERNET");
        }
    }
}
