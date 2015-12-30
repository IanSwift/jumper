package io.pivotal.jumper;


import android.content.Context;
import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class ViewControllerTest {

    @Test
    public void registeredForBroadcastReceivers() {
        JumperView mockDummyView = mock(DummyView.class);
        new ViewController(RuntimeEnvironment.application, mockDummyView, "Bob's data");

        ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);
        List<ShadowApplication.Wrapper> registeredReceivers = shadowApplication.getRegisteredReceivers();
        assertThat(registeredReceivers.get(0).broadcastReceiver).isEqualTo(JumperBroadcastReceiverFactory.getInstance());
        assertThat(registeredReceivers.get(0).intentFilter.getAction(0)).isEqualTo("Bob's data");
    }

    @Test
    public void onUpdate_ifActionNameIsWrong_doesNotDeserialize() {
        ViewDeserializer mockViewDeserializer = mock(ViewDeserializer.class);
        DummyView mockDummyView = mock(DummyView.class);
        stub(mockDummyView.getViewDeserializer()).toReturn(mockViewDeserializer);
        ViewController subject = new ViewController(mock(Context.class), mockDummyView, "Bob's data");

        subject.onUpdate("aDifferentName", mock(Model.class));

        verifyZeroInteractions(mockViewDeserializer);
    }

    @Test
    public void onUpdate_deserializesModel() {
        ViewDeserializer mockViewDeserializer = mock(ViewDeserializer.class);
        JumperView mockView = mock(DummyView.class);
        Model mockModel = mock(Model.class);
        stub(mockView.getViewDeserializer()).toReturn(mockViewDeserializer);

        ViewController subject = new ViewController(mock(Context.class), mockView, "dummyViewName");

        subject.onUpdate("dummyViewName", mockModel);

        verify(mockViewDeserializer).deserialize(mockView, mockModel);
    }

    @Test
    public void notifyObservers() {
        Context mockContext = mock(Context.class);

        ViewController viewController = new ViewController(mockContext, new DummyView(), "Bob's data");
        DefaultModel model = new DefaultModel();
        viewController.setModel(model);

        viewController.notifyObservers();

        ArgumentCaptor<Intent> captor = ArgumentCaptor.forClass(Intent.class);
        verify(mockContext).sendBroadcast(captor.capture());

        Intent value = captor.getValue();
        assertThat(value.getSerializableExtra(JumperBroadcastReceiver.MODEL)).isEqualTo(model);
        assertThat(value.getAction()).isEqualTo("Bob's data");
    }
}