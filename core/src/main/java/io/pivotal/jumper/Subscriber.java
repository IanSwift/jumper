package io.pivotal.jumper;

public interface Subscriber {
    void onUpdate(String actionName, Model model);
}
