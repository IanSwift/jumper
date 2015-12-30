package io.pivotal.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import io.pivotal.jumper.JumperView;
import io.pivotal.jumper.Model;
import io.pivotal.jumper.ViewDeserializer;

public class DescriptionTextView extends TextView implements JumperView {
    public DescriptionTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public ViewDeserializer getViewDeserializer() {
        return new ViewDeserializer() {
            @Override
            public void deserialize(JumperView view, Model model) {
                ((TextView)view).setText(((BreweryResponse)model).getDescription());
            }
        };
    }
}
