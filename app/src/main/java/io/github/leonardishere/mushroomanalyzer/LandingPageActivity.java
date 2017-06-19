package io.github.leonardishere.mushroomanalyzer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class LandingPageActivity extends AppCompatActivity {

    private int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        image = 0;
        final Activity self = this;

        //gets preferences
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        boolean show = sharedPref.getBoolean(getString(R.string.showKey), true);

        //optionally shows
        if(show) {
            //create dialog and show
            View checkBoxView = View.inflate(this, R.layout.checkbox, null);
            final CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(checkBoxView);
            builder.setMessage(R.string.warning)
                    .setTitle("Warning!");

            builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    if (checkBox.isChecked()) {
                        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean(getString(R.string.showKey), false);
                        editor.apply();
                    }
                }
            });
            builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                    self.finish();
                    System.exit(0);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void button1(View view){
        Intent intent = new Intent(this, FeatureFormActivity.class);
        startActivity(intent);
    }

    public void button3(View view){
        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra(getString(R.string.debug), getString(R.string.about));
        startActivity(intent);
    }

    public void button4(View view){
        Intent intent = new Intent(this, DebugActivity.class);
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
