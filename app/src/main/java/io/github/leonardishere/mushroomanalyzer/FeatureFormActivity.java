package io.github.leonardishere.mushroomanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FeatureFormActivity extends AppCompatActivity{

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
        String[] featureNames = new String[]{
            "odor",
            "gill-spacing",
            "spore-print-color",
            "population",
            "habitat"
        };
        String[] features = new String[featureNames.length];
        RadioGroup[] groups = new RadioGroup[]{
                (RadioGroup) findViewById(R.id.radioGroup0),
                (RadioGroup) findViewById(R.id.radioGroup1),
                (RadioGroup) findViewById(R.id.radioGroup4),
                (RadioGroup) findViewById(R.id.radioGroup9),
                (RadioGroup) findViewById(R.id.radioGroup10)
        };
        for(int i = 0; i < features.length; ++i){
            int id = groups[i].getCheckedRadioButtonId();
            if(id == -1){
                features[i] = "none selected";
            }else {
                RadioButton button = (RadioButton) findViewById(id);
                String str = (String) button.getText();
                features[i] = str;
            }
        }
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(getString(R.string.features), features);
        startActivity(intent);
    }
}
