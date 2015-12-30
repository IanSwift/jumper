package io.pivotal.demo;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class, sdk=18)
public class MyActivityTest {

    private MyActivity myActivity;

    @Before
    public void setup() {
        myActivity = Robolectric.setupActivity(MyActivity.class);
    }

    @Test
    public void buttonStartsService() {
        myActivity.findViewById(R.id.my_button).performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(myActivity);
        Intent nextStartedService = shadowActivity.getNextStartedService();

        assertThat(nextStartedService).isNotNull();

        assertThat(Shadows.shadowOf(nextStartedService).getIntentClass()).isEqualTo(ApiCallService.class);
    }
}