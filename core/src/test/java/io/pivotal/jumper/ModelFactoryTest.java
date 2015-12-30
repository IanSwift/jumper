package io.pivotal.jumper;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=18)
public class ModelFactoryTest {

    private ModelFactory modelFactory;

    @Before
    public void setup() {
        modelFactory = new ModelFactory();
    }

    @Test
    public void newInstance_createsNewInstance() {
        Object model = modelFactory.newInstance(mock(Context.class), DummyInterface.class, new DefaultModel(), "Dummy Name");
        assertThat(model).isNotNull();
    }

    @Test
    public void newInstance_firesBroadcast() {
        Context mockContext = mock(Context.class);
        DefaultModel model = new DefaultModel();
        modelFactory.newInstance(mockContext, DummyInterface.class, model, "DummyName");

        ArgumentCaptor<Intent> captor = ArgumentCaptor.forClass(Intent.class);
        verify(mockContext).sendBroadcast(captor.capture());

        Intent value = captor.getValue();
        assertThat(value.getSerializableExtra(JumperBroadcastReceiver.MODEL)).isEqualTo(model);
        assertThat(value.getAction()).isEqualTo("DummyName");
    }
}