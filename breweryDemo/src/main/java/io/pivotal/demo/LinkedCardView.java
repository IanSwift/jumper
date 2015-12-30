package io.pivotal.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;

import io.pivotal.jumper.JumperView;
import io.pivotal.jumper.Model;
import io.pivotal.jumper.ViewDeserializer;

public class LinkedCardView extends CardView implements JumperView {

    private final Context context;

    public LinkedCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public ViewDeserializer getViewDeserializer() {
        return new ViewDeserializer() {
            @Override
            public void deserialize(JumperView view, final Model model) {
                ((LinkedCardView)view).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(((BreweryResponse) model).getWebsite())));
                    }
                });
            }
        };
    }
}
