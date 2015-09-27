package com.mscourse.boosting;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.J48;

import com.mscourse.MLProgramSuite;
import com.mscourse.algo.AlgoTest;

public class BoostingTest extends AlgoTest {

	private static void printmenu(){
		System.out.println("Type 0 - to exit");
	    System.out.println("Type 1 - to run AdaBoosting on J4.8 tree ");
	    System.out.println("Type 2 - to run AdaBoosting on SVM");
	    System.out.println("===========================");
	}
	
	/**
	 * 
	 */
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
			    	System.out.println("Type 1 - to run AdaBoosting on J4.8 tree ");
			    	testBoostingOnTree();
			          break;
			    case 2: 
			    	System.out.println("Type 2 - to run AdaBoosting on SVM");
			    	testBoostingOnSVM();
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
	
	private void testBoostingOnSVM() {
		try {
			String[] options = new String[1];
			AdaBoostM1 boosting = new AdaBoostM1();
			
			SMO smo = new SMO();
			boosting.setClassifier(smo);
			boosting.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(boosting.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(boosting, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			
			
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(boosting);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testBoostingOnTree(){
		try {
			String[] options = new String[1];
			AdaBoostM1 boosting = new AdaBoostM1();
			
			J48 tree = new J48();
			boosting.setClassifier(tree);
			boosting.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(boosting.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(boosting, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(boosting);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
