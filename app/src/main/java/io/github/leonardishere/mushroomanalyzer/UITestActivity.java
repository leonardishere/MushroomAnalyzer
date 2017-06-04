package io.github.leonardishere.mushroomanalyzer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;

public class UITestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitest);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.feature20_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.feature20_array, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter1);

        ExpandableListView listView2 = (ExpandableListView) findViewById(R.id.listView2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.feature20_array, android.R.layout.simple_expandable_list_item_1);
        //listView2.setAdapter(adapter2);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {}

    public void onNothingSelected(AdapterView<?> parent) {}
}
