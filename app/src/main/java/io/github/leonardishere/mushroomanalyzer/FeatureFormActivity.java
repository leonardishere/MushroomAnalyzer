package io.github.leonardishere.mushroomanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FeatureFormActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_form);
    }

    /**
     * Enables or disables the next button if all radio groups have a selection.
     * @param view the radiobutton that was clicked
     */
    public void onRadioButtonClicked(View view){
        RadioGroup[] groups = new RadioGroup[]{
                (RadioGroup) findViewById(R.id.radioGroup0),
                (RadioGroup) findViewById(R.id.radioGroup1),
                (RadioGroup) findViewById(R.id.radioGroup2),
                (RadioGroup) findViewById(R.id.radioGroup3),
                (RadioGroup) findViewById(R.id.radioGroup4)
        };
        boolean allSelected = true;
        for(int i = 0; i < groups.length; ++i){
            int id = groups[i].getCheckedRadioButtonId();
            if(id == -1){
                allSelected = false;
            }
        }
        Button button = (Button) findViewById(R.id.nextButton);
        button.setEnabled(allSelected);
    }

    /**
     * Gets the values from the form and sends them to a new ResultsActivity.
     * @param view the next button
     */
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
                (RadioGroup) findViewById(R.id.radioGroup2),
                (RadioGroup) findViewById(R.id.radioGroup3),
                (RadioGroup) findViewById(R.id.radioGroup4)
        };
        //iterate over every radio group
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
