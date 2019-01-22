/**
 * 
 */
package com.ml.dtree;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.ml.MLProgramSuite;
import com.ml.algo.AlgoTest;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.neural.*;
import weka.core.Instances;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * 
 * Especially repeated randomizations, 
 * e.g., as during cross-validation, help to generate more realistic statistics.
 * http://cs.nyu.edu/courses/spring02/G22.2560-001/prog2.html
 * @author kmanda1
 * 
 */
public class J48Test extends AlgoTest {
	
	private int minNumObj = 2;
	
	public static void main(String[] args) {
		J48Test sample1 = new J48Test();
		//sample1.wekaProcessFlow();
	}
	
	public void testBoosting(){
	   //	http://alecmgo-school.googlecode.com/svn/trunk/cs224u/wsd/src/com/cs224u/Wsd.java
		// http://machinelearningmastery.com/improve-machine-learning-results-with-boosting-bagging-and-blending-ensemble-methods-in-weka/
	}
	
	private static void printmenu(){
		System.out.println("Type 0 - to exit");
	    System.out.println("Type 1 - to run J48 (unpruned).");
	    System.out.println("Type 2 - to run J48 (pruned).");
	    System.out.println("Type 3 - to run J48 (reduced-error pruning)");
	    System.out.println("Type 4 - to run J48 (folding on unpruned tree)");
	    System.out.println("Type 5 - to run J48 (folding and pruning)");
	    //System.out.println("Type 6 - to run J48 (tests with folding).");
	    System.out.println("===========================");
	}
	
	public void runWorkFlow(){
	    InputStreamReader str= new InputStreamReader (System.in);
	    BufferedReader uinp= new BufferedReader (str);
	    String val;
	    int option;
	    
	    try {
		    System.out.println("What's the minimum number of instances per leaf ?");
		    val= uinp.readLine();
		    minNumObj = Integer.parseInt(val);
	   
			while (true) {
				printmenu();
			    val= uinp.readLine();
			    option = Integer.parseInt(val);
			    if(option == 0) {
			    	System.out.println("Return to main menu.");
			    	break;
			    }
			    switch (option) {
			    case 1:
			    	//Unpruned Tree
			    	System.out.println("Apply unpruned J4.8 algo");
			    	testSimpleClassification();
			          break;
			    case 2: 
			    	//Pruned Tree
			    	System.out.println("Apply J4.8 algo with pruning");
			    	testSimpleClassificationWithPruning();
			          break;
			    case 3:
			    	System.out.println("Apply J4.8 algo with reduced-error pruning");
			    	testSimpleClassificationWithReducedPruning();
			    	break;
			    case 4:   
			    	System.out.println("Apply J4.8 algo with folding on unpruned tree");
			    	testUnprunedClassificationWithFolding();
			          break;
			    case 5:   
			    	System.out.println("Apply J4.8 algo with pruning and folding");
			    	testClassificationWithPruningAndFolding();
			          break;   
			    case 6:   
			    	//System.out.println("Apply J4.8 algo with tests");
			    	//testsWithFoldingAndTestData();
			          break;        
			    default:
			    	 printmenu();
			    	 break;
			  }
			}
		} catch (Exception e) {
			printmenu();
			System.exit(1);
		}
	}

	public void testSimpleClassification(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			J48 tree = new J48(); // new instance of tree
			tree.setMinNumObj(minNumObj);
			tree.setUnpruned(true);
			tree.setReducedErrorPruning(false);
			tree.setSubtreeRaising(false);
			tree.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(tree.toSummaryString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(tree, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testSimpleClassificationWithPruning(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			J48 tree = new J48(); // new instance of tree
			tree.setMinNumObj(minNumObj);
			tree.setReducedErrorPruning(false);
			tree.setSubtreeRaising(true);
			tree.setUnpruned(false);
			tree.buildClassifier(MLProgramSuite.TRAINING_DATA);
			 System.out.println(tree.toSummaryString());
			 
			 Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
		     eval.evaluateModel(tree, MLProgramSuite.TRAINING_DATA);
		     System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			 
		     System.out.println("---- Now apply the Classifier to the test data --- ");
				applyAlgoToTestData(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSimpleClassificationWithReducedPruning(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			J48 tree = new J48(); // new instance of tree
			tree.setMinNumObj(minNumObj);
			tree.setReducedErrorPruning(true);
			tree.setNumFolds(3);
			tree.setSubtreeRaising(true);
			tree.setUnpruned(false);
			tree.buildClassifier(MLProgramSuite.TRAINING_DATA);
			 System.out.println(tree.toSummaryString());
			 
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(tree, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));

			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(tree);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void classifyInstances(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			options[0] = "-U"; // unpruned tree
			J48 tree = new J48(); // new instance of tree
			tree.setMinNumObj(minNumObj);
			tree.setOptions(options); // set the options
			tree.buildClassifier(MLProgramSuite.TRAINING_DATA); // build classifier
			// 
			for (int i = 0; i < MLProgramSuite.TEST_DATA.numInstances(); i++) {
				double pred = tree.classifyInstance(MLProgramSuite.TEST_DATA.instance(i)); 
				double actual = MLProgramSuite.TEST_DATA.instance(i).classValue(); 
				System.out.print("ID: "
				+ MLProgramSuite.TEST_DATA.instance(i).value(0)); 
				System.out.print(", actual: "
				+ MLProgramSuite.TEST_DATA.classAttribute().value((int) actual)); 
				System.out.println(", predicted: "
				+ MLProgramSuite.TEST_DATA.classAttribute().value((int) pred)); 
			}

			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(tree);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testUnprunedClassificationWithFolding(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			J48 tree = new J48(); // new instance of tree
			tree.setMinNumObj(minNumObj);
			tree.setUnpruned(true);
			tree.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(tree.toSummaryString());
			// 
			 Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			 eval.crossValidateModel(tree, MLProgramSuite.TRAINING_DATA, 20, new Random(1));
			//
			 System.out.println(eval.toSummaryString("\nResults\n\n", false));
			 
			 System.out.println("---- Now apply the Classifier to the test data --- ");
				applyAlgoToTestData(tree);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testClassificationWithPruningAndFolding(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			J48 tree = new J48(); // new instance of tree
			tree.setMinNumObj(minNumObj);
			tree.setReducedErrorPruning(true);
			tree.setNumFolds(3);
			tree.setSubtreeRaising(true);
			tree.setUnpruned(false);
			tree.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(tree.toSummaryString());
			// 
			 Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			 eval.crossValidateModel(tree, MLProgramSuite.TRAINING_DATA, 20, new Random(1));
			//
			 System.out.println(eval.toSummaryString("\nResults\n\n", false));
			 
			 System.out.println("---- Now apply the Classifier to the test data --- ");
				applyAlgoToTestData(tree);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
