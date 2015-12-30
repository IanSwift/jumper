package io.pivotal.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import io.pivotal.jumper.JumperView;
import io.pivotal.jumper.Model;
import io.pivotal.jumper.ViewDeserializer;

public class NameTextView extends TextView implements JumperView {

    private final Context context;

    public NameTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public ViewDeserializer getViewDeserializer() {
        return new ViewDeserializer() {
            @Override
            public void deserialize(JumperView view, final Model model) {
                ((TextView) view).setText(((BreweryResponse)model).getName());
            }
        };
    }
}
