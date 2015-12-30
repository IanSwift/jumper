package io.pivotal.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.pivotal.jumper.JumperView;
import io.pivotal.jumper.ViewController;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        findViewById(R.id.my_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MyActivity.this, ApiCallService.class));
            }
        });

        new ViewController(this, (JumperView) findViewById(R.id.name_view), "BREWERY");
        new ViewController(this, (JumperView) findViewById(R.id.description_view), "BREWERY");
        new ViewController(this, (JumperView) findViewById(R.id.card_view), "BREWERY");
    }
}
