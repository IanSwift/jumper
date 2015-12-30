package io.pivotal.jumper;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ViewController implements Subscriber {
    private Context context;
    private String name;
    private JumperView view;
    private ViewDeserializer viewDeserializer;
    private Model model;

    public ViewController(Context context, JumperView view, String name) {
        this.view = view;
        this.viewDeserializer = view.getViewDeserializer();
        this.context = context;
        this.name = name;

        context.registerReceiver(JumperBroadcastReceiverFactory.getInstance(), new IntentFilter(name));
        JumperBroadcastReceiverFactory.getInstance().register(this);
    }

    @Override
    public void onUpdate(String actionName, Model model) {
        if (viewDeserializer == null || !actionName.equals(name))
            return;
        viewDeserializer.deserialize(view, model);
    }

    public void notifyObservers() {
        Intent intent = new Intent(name);
        intent.putExtra(JumperBroadcastReceiver.MODEL, model);
        context.sendBroadcast(intent);
    }

    void setModel(Model model) {
        this.model = model;
    }
}
