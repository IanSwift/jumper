package io.pivotal.demo;

import android.app.Application;

import io.pivotal.demo.GraphProvider;
import io.pivotal.demo.MyModule;

public class BreweryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GraphProvider.getInstance().setupGraph(MyModule.class);
    }
}
