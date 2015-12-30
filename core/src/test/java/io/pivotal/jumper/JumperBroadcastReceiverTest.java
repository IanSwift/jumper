package io.pivotal.jumper;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class JumperBroadcastReceiverTest {

    private JumperBroadcastReceiver subject;

    @Before
    public void setUp() {
        subject = new JumperBroadcastReceiver();
    }

    @Test
    public void register_registersSubscriberWithBroadcastReceiver() {
        subject.register(new DefaultSubscriber());

        assertThat(subject.getRegisteredReceivers().size()).isEqualTo(1);
    }

    @Test
    public void registeredReceiver_receivesBroadcast() {
        DefaultSubscriber defaultSubscriber = mock(DefaultSubscriber.class);
        subject.register(defaultSubscriber);

        Intent mock = mock(Intent.class);

        stub(mock.getAction()).toReturn("model_name");
        Model model = mock(Model.class);
        stub(mock.getSerializableExtra("JumperBroadcastReceiver.MODEL")).toReturn(model);
        subject.onReceive(mock(Context.class), mock);

        verify(defaultSubscriber).onUpdate("model_name", model);

        stub(mock.getAction()).toReturn("other_name");
        Model otherModel = mock(Model.class);
        stub(mock.getSerializableExtra(JumperBroadcastReceiver.MODEL)).toReturn(otherModel);
        subject.onReceive(mock(Context.class), mock);

        verify(defaultSubscriber).onUpdate("other_name", otherModel);
    }
}