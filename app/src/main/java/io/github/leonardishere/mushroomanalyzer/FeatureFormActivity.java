package io.github.leonardishere.mushroomanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FeatureFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_form);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.feature20_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        /*
        String str = (String) parent.getItemAtPosition(pos);

        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra(getString(R.string.debug), str);
        startActivity(intent);
        */
    }

    public void onNothingSelected(AdapterView<?> parent) {
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
        for(int i = 0; i < 10; ++i){
            int id = groups[i].getCheckedRadioButtonId();
            if(id == -1){
                text = String.format("%s\n%s=%s", text, features[i], "none selected");
            }else {
                RadioButton button = (RadioButton) findViewById(id);
                String str = (String) button.getText();
                text = String.format("%s\n%s=%s", text, features[i], str);
            }
        }
        text = String.format("%s\n%s", text, "next step not implemented yet. please stand by.");

        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra(getString(R.string.debug), text);
        startActivity(intent);
    }
}
