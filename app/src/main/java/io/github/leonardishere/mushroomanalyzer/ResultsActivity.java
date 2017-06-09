package io.github.leonardishere.mushroomanalyzer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import weka.classifiers.functions.Logistic;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String[] features = intent.getStringArrayExtra(getString(R.string.features));
        TextView view2 = (TextView) findViewById(R.id.resultText2);
        TextView view3 = (TextView) findViewById(R.id.resultText3);

        double prediction = predict(features);
        String similar = getSimilar(features);
        String status;
        if(prediction == 0) status = "edible";
        else if(prediction == 1) status = "poisonous";
        else status = "unknown";

        view2.setText(status);
        view3.setText(similar);
    }

    /**
     * Maps the string array of features to their representative chars.
     * @param features the array of features
     * @return the representative chars
     */
    public String[] featuresStringToChar(String[] features){
        String[] newFeatures = new String[5];
        switch(features[0]){
            case "almond":
                newFeatures[0] = "a";
                break;
            case "anise":
                newFeatures[0] = "l";
                break;
            case "creosote":
                newFeatures[0] = "c";
                break;
            case "fishy":
                newFeatures[0] = "y";
                break;
            case "foul":
                newFeatures[0] = "f";
                break;
            case "musty":
                newFeatures[0] = "m";
                break;
            case "none":
                newFeatures[0] = "n";
                break;
            case "pungent":
                newFeatures[0] = "p";
                break;
            case "spicy":
                newFeatures[0] = "s";
                break;
            default:
                newFeatures[0] = "?";
                break;
        }

        switch(features[1]){
            case "close":
                newFeatures[1] = "c";
                break;
            case "crowded":
                newFeatures[1] = "w";
                break;
            case "distant":
                newFeatures[1] = "d";
                break;
            default:
                newFeatures[1] = "?";
                break;
        }

        switch(features[2]){
            case "ibrous":
                newFeatures[2] = "f";
                break;
            case "scaly":
                newFeatures[2] = "y";
                break;
            case "silky":
                newFeatures[2] = "k";
                break;
            case "smooth":
                newFeatures[2] = "s";
                break;
            default:
                newFeatures[2] = "?";
                break;
        }

        switch(features[3]){
            case "black":
                newFeatures[3] = "k";
                break;
            case "brown":
                newFeatures[3] = "n";
                break;
            case "buff":
                newFeatures[3] = "b";
                break;
            case "chocolate":
                newFeatures[3] = "h";
                break;
            case "green":
                newFeatures[3] = "r";
                break;
            case "orange":
                newFeatures[3] = "o";
                break;
            case "purple":
                newFeatures[3] = "u";
                break;
            case "white":
                newFeatures[3] = "w";
                break;
            case "yellow":
                newFeatures[3] = "y";
                break;
            default:
                newFeatures[3] = "?";
                break;
        }

        switch(features[4]){
            case "abundant":
                newFeatures[4] = "a";
                break;
            case "clustered":
                newFeatures[4] = "c";
                break;
            case "numerous":
                newFeatures[4] = "n";
                break;
            case "scattered":
                newFeatures[4] = "s";
                break;
            case "several":
                newFeatures[4] = "v";
                break;
            case "solitary":
                newFeatures[4] = "y";
                break;
            default:
                newFeatures[4] = "?";
                break;
        }

        /*
        Intent intent = new Intent(this, DebugActivity.class);
        String str = String.format("%s\n%s\n%s\n%s\n%s\n%s", "features to char returned:", newFeatures[0], newFeatures[1], newFeatures[2], newFeatures[3], newFeatures[4]);
        intent.putExtra(getString(R.string.features), str);
        startActivity(intent);
        */
        return newFeatures;
    }

    /**
     * Predicts the edibility of the mushroom.
     * 0 is edible, 1 is poisonous, and other values are errors.
     * @param features the features of the mushroom
     * @return edibility
     */
    public double predict(String[] features) {
        Logistic lg2 = null;
        //deserializes a pre-trained Logistic object from file
        try{
            Resources resources = getResources();
            InputStream filein = resources.openRawResource(R.raw.classifier);

            ObjectInputStream in = new ObjectInputStream(filein);
            Object obj = in.readObject();
            lg2 = (Logistic) obj;
            in.close();
            filein.close();
        }catch(IOException ioex){
            return -1;
        }catch(ClassNotFoundException cnfex){
            return -2;
        }

        if(lg2 != null){
            //Declaring nominal attributes

            // Attribute 5
            FastVector fvNominalVal = new FastVector(9);
            fvNominalVal.addElement("a");
            fvNominalVal.addElement("c");
            fvNominalVal.addElement("f");
            fvNominalVal.addElement("l");
            fvNominalVal.addElement("m");
            fvNominalVal.addElement("n");
            fvNominalVal.addElement("p");
            fvNominalVal.addElement("s");
            fvNominalVal.addElement("y");
            Attribute odor = new Attribute("odor", fvNominalVal);

            // Attribute 7
            FastVector fvNominalVal1 = new FastVector(3);
            fvNominalVal1.addElement("c");
            fvNominalVal1.addElement("d");
            fvNominalVal1.addElement("w");
            Attribute gillspacing = new Attribute("gill-spacing", fvNominalVal1);

            // Attribute 12
            FastVector fvNominalVal2 = new FastVector(4);
            fvNominalVal2.addElement("f");
            fvNominalVal2.addElement("k");
            fvNominalVal2.addElement("s");
            fvNominalVal2.addElement("y");
            Attribute stalksurfacearing = new Attribute("stalk-surface-above-ring", fvNominalVal2);

            // Attribute 20
            FastVector fvNominalVal3 = new FastVector(9);
            fvNominalVal3.addElement("b");
            fvNominalVal3.addElement("h");
            fvNominalVal3.addElement("k");
            fvNominalVal3.addElement("n");
            fvNominalVal3.addElement("o");
            fvNominalVal3.addElement("r");
            fvNominalVal3.addElement("u");
            fvNominalVal3.addElement("w");
            fvNominalVal3.addElement("y");
            Attribute sporeprintcolor = new Attribute("spore-print-color", fvNominalVal3);

            // Attribute 21
            FastVector fvNominalVal4 = new FastVector(6);
            fvNominalVal4.addElement("a");
            fvNominalVal4.addElement("c");
            fvNominalVal4.addElement("n");
            fvNominalVal4.addElement("s");
            fvNominalVal4.addElement("v");
            fvNominalVal4.addElement("y");
            Attribute population = new Attribute("population", fvNominalVal4);

            // Declare the class attribute along with its values contains two nominal values yes and no using FastVector. "ScheduledFirst" is the name of the class attribute
            FastVector fvClassVal = new FastVector(2);
            fvClassVal.addElement("e");  //output is 0
            fvClassVal.addElement("p");  //output is 1
            Attribute Class = new Attribute("class", fvClassVal);

            // Declare the feature vector

            FastVector fvWekaAttributes = new FastVector(6);
            // Add attributes
            fvWekaAttributes.addElement(odor);
            fvWekaAttributes.addElement(gillspacing);
            fvWekaAttributes.addElement(stalksurfacearing);
            fvWekaAttributes.addElement(sporeprintcolor);
            fvWekaAttributes.addElement(population);
            fvWekaAttributes.addElement(Class);
            // Declare Instances which is required since I want to use classification/Prediction
            Instances testInstance = new Instances("temp", fvWekaAttributes, 0);

            //Create the new instance i1 to test
            String[] newFeatures = featuresStringToChar(features);
            Instance i1 = new DenseInstance(6);
            i1.setValue(odor, newFeatures[0]);
            i1.setValue(gillspacing, newFeatures[1]);
            i1.setValue(stalksurfacearing, newFeatures[2]);
            i1.setValue(sporeprintcolor, newFeatures[3]);
            i1.setValue(population, newFeatures[4]);

            // add instance to test instances
            testInstance.add(i1);
            testInstance.setClassIndex(5);

            // find class value
            i1.setDataset(testInstance);
            i1.setClassMissing();
            try {
                i1.setClassValue(lg2.classifyInstance(testInstance.get(0)));
            }catch(Exception ex){
                ex.printStackTrace();
            }
            // the thing you really need!!!!
            double result = i1.classValue();
            //System.out.println(result);
            return result;
            //0.0 == e
            //1.0 == p
        }else{
            return -3;
        }
    }

    /**
     * Finds any similar mushrooms in the database.
     * @param features the features of a given mushroom
     * @return a formatted string with data about similar mushrooms
     */
    public String getSimilar(String[] features){
        String[] charFeatures = featuresStringToChar(features);
        String featureString = String.format(Locale.getDefault(), "%s%s%s%s%s", charFeatures[0], charFeatures[1], charFeatures[2], charFeatures[3], charFeatures[4]);

        //reads in the text file
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        ArrayList<Integer> arr3 = new ArrayList<>();
        try{
            Resources resources = getResources();
            InputStream filein = resources.openRawResource(R.raw.mushrooms2);
            Scanner scan = new Scanner(filein);
            while(scan.hasNext()){
                arr1.add(scan.next());
                arr2.add(scan.next());
                arr3.add(scan.nextInt());
            }

            filein.close();
        }catch(IOException ioex){
            return "ioexception";
        }

        for(int i = 0; i < arr1.size(); ++i){
            if(featureString.equals(arr1.get(i))){
                return String.format(Locale.getDefault(), "%d similar %s mushrooms were found", arr3.get(i), arr2.get(i).equals("e") ? "edible" : "poisonous");
            }
        }
        //return "no similar mushrooms were found";
        return "";
    }
}
