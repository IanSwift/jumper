package io.pivotal.jumper;

public class JumperBroadcastReceiverFactory {

    private static JumperBroadcastReceiver instance;

    public static JumperBroadcastReceiver getInstance() {
        if (instance == null) {
            instance = new JumperBroadcastReceiver();
        }
        return instance;
    }
}
