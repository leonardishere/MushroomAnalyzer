import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.*;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * ClassifierCreator is used to create a Weka classifier and save it to file.
 * @author Andrew Leonard
 *
 */
public class ClassifierCreator {
	public static void main(String[] args) throws Exception{
		// import train data
		DataSource source = new DataSource("mushroom.arff");
		Instances train = source.getDataSet();

		// set train class index
		train.setClassIndex(train.numAttributes() - 1);
		train.randomize(new Random(1));

		// Filter out unwanted attributes
		Remove remove;
		remove = new Remove();
		remove.setAttributeIndices("1-4,6,8-11,13-19,22");
		remove.setInvertSelection(false);
		remove.setInputFormat(train);
		train = Filter.useFilter(train, remove);

		// set new class index
		train.setClassIndex(5);
		
		// create classifier
		Logistic lg = new Logistic();
		lg.buildClassifier(train);

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
		Instance i1 = new DenseInstance(6);
		i1.setValue(odor, "p");
		i1.setValue(gillspacing, "c");
		i1.setValue(stalksurfacearing, "s");
		i1.setValue(sporeprintcolor, "k");
		i1.setValue(population, "s");
		
		
		// add instance to test instances
		testInstance.add(i1);
		testInstance.setClassIndex(5);
		
		// find class value
		i1.setDataset(testInstance);
		i1.setClassMissing();
		i1.setClassValue(lg.classifyInstance(testInstance.get(0)));
		
		// the thing you really need!!!!
		double result = i1.classValue();
		System.out.println(result);
		//0.0 == e
		//1.0 == p
		
		
		//write serialized Logistic to file
		String filepath = "classifier.ser";
		try{
			FileOutputStream fileout = new FileOutputStream(filepath);
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(lg);
			out.close();
			fileout.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}