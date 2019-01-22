package com.ml.knn;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

import com.ml.MLProgramSuite;
import com.ml.algo.AlgoTest;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.NormalizedPolyKernel;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class KNNTest extends AlgoTest {
	
	private static void printmenu(){
		System.out.println("Type 0 - to exit");
	    System.out.println("Type 1 - to run KNN with k = 1");
	    System.out.println("Type 2 - to run KNN with k = 3");
	    System.out.println("===========================");
	}
	
	public void runWorkFlow(){
	    InputStreamReader str= new InputStreamReader (System.in);
	    BufferedReader uinp= new BufferedReader (str);
	    String val;
	    int option;
	    try {
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
			    	System.out.println("Type 1 - to run KNN with k = 3.");
			    	testKNNwithCrossValidation();
			          break;
			    case 2: 
			    	//Pruned Tree
			    	System.out.println("Type 2 - to run KNN with k = 9.");
			    	testKNNwithLargeSetOfNeighboursAndCrossValidation();
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
	
	public void testSimpleKNN(){
		try {
			String[] options = new String[1];
			IBk ibk = new IBk();
			ibk.setKNN(3);
			ibk.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(ibk.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(ibk, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testKNNwithCrossValidation(){
		try {
			String[] options = new String[1];
			IBk ibk = new IBk();
			ibk.setKNN(3);
			ibk.setCrossValidate(true);
			ibk.setMeanSquared(true);
			ibk.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(ibk.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(ibk, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(ibk);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testKNNwithLargeSetOfNeighboursAndCrossValidation(){
		try {
			String[] options = new String[1];
			
			InputStreamReader str= new InputStreamReader (System.in);
		    BufferedReader uinp= new BufferedReader (str);
		    System.out.println("How many neighbours ? ");
		    String val= uinp.readLine();
		    int k = Integer.parseInt(val);
			//
			IBk ibk = new IBk();
			ibk.setKNN(k);
			ibk.setCrossValidate(true);
			ibk.setMeanSquared(true);
			ibk.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(ibk.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(ibk, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			//
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(ibk);
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
