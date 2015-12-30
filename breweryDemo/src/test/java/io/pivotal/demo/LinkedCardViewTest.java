package io.pivotal.demo;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowIntent;

import java.util.ArrayList;

import io.pivotal.jumper.ViewDeserializer;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class, sdk=18)
public class LinkedCardViewTest {

    @Test
    public void deserializeView() {
        LinkedCardView subject = new LinkedCardView(RuntimeEnvironment.application, null);
        ViewDeserializer viewDeserializer = subject.getViewDeserializer();
        BreweryResponse model = new BreweryResponse();
        ArrayList<BreweryResponse.Brewery> data = new ArrayList<>();
        data.add(new BreweryResponse.Brewery());
        model.setData(data);
        model.setWebsite("http://www.example.com");
        viewDeserializer.deserialize(subject, model);

        subject.performClick();

        ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);
        Intent nextStartedActivity = shadowApplication.getNextStartedActivity();

        assertThat(nextStartedActivity).isNotNull();

        ShadowIntent shadowIntent = Shadows.shadowOf(nextStartedActivity);

        assertThat(shadowIntent.getAction()).isEqualTo(Intent.ACTION_VIEW);
        assertThat(shadowIntent.toURI()).isEqualTo("http://www.example.com");
    }
}