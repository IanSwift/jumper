package io.pivotal.jumper;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModelInvocationHandler implements InvocationHandler {

    private final Model model;
    private final Context context;
    private final Class<?> interfaceClass;
    private final String name;

    public ModelInvocationHandler(Class<?> interfaceClass, Context context, Model model, String name) {
        this.model = model;
        this.context = context;
        this.interfaceClass = interfaceClass;
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = callMethodOnOriginal(method, args);

        Intent intent = new Intent();
        intent.putExtra(JumperBroadcastReceiver.MODEL, model);
        intent.setAction(name);
        context.sendBroadcast(intent);

        return result;
    }

    protected Object callMethodOnOriginal(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(model, args);
    }
}
