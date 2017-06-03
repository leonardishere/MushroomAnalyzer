package io.github.leonardishere.mushroomanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        Intent intent = getIntent();
        String str = intent.getStringExtra("debug_text");
        TextView view = (TextView) findViewById(R.id.debugTextview);
        view.setText(str);
    }
}
