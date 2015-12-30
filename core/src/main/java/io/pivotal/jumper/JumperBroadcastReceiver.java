package io.pivotal.jumper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class JumperBroadcastReceiver extends BroadcastReceiver{
    public static final String MODEL = "JumperBroadcastReceiver.MODEL";
    public static final String NAME = "io.pivotal.jumper.JumperBroadCastReceiver.NAME";

    private List<Subscriber> registeredReceivers = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        for (Subscriber receiver : registeredReceivers) {
            receiver.onUpdate(intent.getAction(), (Model) intent.getSerializableExtra(MODEL));
        }
    }

    public void register(Subscriber context) {
        registeredReceivers.add(context);
    }

    List<Subscriber> getRegisteredReceivers() {
        return registeredReceivers;
    }
}
