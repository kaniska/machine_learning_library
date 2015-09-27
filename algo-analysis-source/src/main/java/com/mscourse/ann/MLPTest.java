package com.mscourse.ann;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

import com.mscourse.MLProgramSuite;
import com.mscourse.algo.AlgoTest;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.NormalizedPolyKernel;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class MLPTest  extends AlgoTest {
	
	private static void printmenu(){
		System.out.println("Type 0 - to exit");
	    System.out.println("Type 1 - to run MLP with with learning rate 0.3");
	    System.out.println("Type 2 - to run MLP with learning rate 0.1");
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
			    	//Unpruned Tree
			    	System.out.println("Type 1 - to run MLP with learning rate 0.3.");
			    	testMLP();
			          break;
			    case 2: 
			    	//Pruned Tree
			    	System.out.println("Type 2 - to run MLP with learning rate 0.1");
			    	testMLPwithSlowLearningRate();
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
	
	public void testMLP(){
		try {
			String[] options = new String[1];
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			mlp.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(mlp.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(mlp, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(mlp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testMLPwithSlowLearningRate(){
		try {
			String[] options = new String[1];
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			mlp.setLearningRate(0.1);
			mlp.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(mlp.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(mlp, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(mlp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}