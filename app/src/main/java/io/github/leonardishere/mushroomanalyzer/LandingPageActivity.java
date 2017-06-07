package io.github.leonardishere.mushroomanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LandingPageActivity extends AppCompatActivity {

    private int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        image = 0;
    }

    public void button1(View view){
        Intent intent = new Intent(this, FeatureFormActivity.class);
        startActivity(intent);
    }

    public void button2(View view){

        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra(getString(R.string.debug), "button2 was clicked");
        startActivity(intent);
        /*
        Intent intent = new Intent(this, UITestActivity.class);
        startActivity(intent);
        */
    }

    public void button3(View view){
        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra(getString(R.string.debug), "button3 was clicked");
        startActivity(intent);
    }

    public void button4(View view){
        Intent intent = new Intent(this, DebugActivity.class);
        //intent.putExtra("debug_text", "button4 was clicked");
        intent.putExtra(getString(R.string.debug), getString(R.string.warning));
        startActivity(intent);
    }

    public void imageClick(View view){
        ImageView imageView = (ImageView) view;
        ++image;
        if(image%2 == 0)
            imageView.setImageResource(R.drawable.white);
        else
            imageView.setImageResource(R.drawable.red);
    }
}
