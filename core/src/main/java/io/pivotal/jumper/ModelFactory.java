package io.pivotal.jumper;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Proxy;

public class ModelFactory {
    public Object newInstance(Context context, Class<?> interfaceType, Model original, String name) {
        Object proxy = Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[]{interfaceType}, new ModelInvocationHandler(interfaceType, context, original, name));

        Intent intent = new Intent(name);
        intent.putExtra(JumperBroadcastReceiver.MODEL, original);
        context.sendBroadcast(intent);
        return proxy;
    }
}
