package io.github.leonardishere.mushroomanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FeatureFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_form);
    }

    public void help(View view){
        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra(getString(R.string.debug), "help isn't coming");
        startActivity(intent);
    }

    public void next(View view){
        String[] features = new String[]{
                "odor",
                "gill-spacing",
                "gill-size",
                "gill-color",
                "stalk-surface-above-ring",
                "stalk-surface-below-ring",
                "stalk-color-above-ring",
                "stalk-color-below-ring",
                "veil-color",
                "spore-print-color"
        };
        RadioGroup[] groups = new RadioGroup[]{
            (RadioGroup) findViewById(R.id.radioGroup0),
            (RadioGroup) findViewById(R.id.radioGroup1),
            (RadioGroup) findViewById(R.id.radioGroup2),
            (RadioGroup) findViewById(R.id.radioGroup3),
            (RadioGroup) findViewById(R.id.radioGroup4),
            (RadioGroup) findViewById(R.id.radioGroup5),
            (RadioGroup) findViewById(R.id.radioGroup6),
            (RadioGroup) findViewById(R.id.radioGroup7),
            (RadioGroup) findViewById(R.id.radioGroup8),
            (RadioGroup) findViewById(R.id.radioGroup9)
        };

        String text = "You selected:";
        /*
        RadioGroup group0 = (RadioGroup) findViewById(R.id.radioGroup0);
        int id0 = group0.getCheckedRadioButtonId();
        RadioButton button = (RadioButton) findViewById(id0);
        String str0 = (String) button.getText();
        text = String.format("%s\n%s", text, str0);
        */
        for(int i = 0; i < 10; ++i){
            int id = groups[i].getCheckedRadioButtonId();
            RadioButton button = (RadioButton) findViewById(id);
            String str = (String) button.getText();
            text = String.format("%s\n%s=%s", text, features[i], str);
        }

        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra(getString(R.string.debug), text);
        startActivity(intent);
    }
}
