/**
 * 
 */
package com.mscourse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.mscourse.ann.MLPTest;
import com.mscourse.boosting.BoostingTest;
import com.mscourse.dtree.J48Test;
import com.mscourse.knn.KNNTest;
import com.mscourse.svm.SVMTest;

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
public class MLProgramSuite {
	
	public static Instances TRAINING_DATA = null;
	public static Instances TEST_DATA = null;

	private static void setup(String trainingFile, String testFile) {
		try {
		ClassPathResource resource1 = new ClassPathResource(trainingFile);
		DataSource source1 = new DataSource(resource1.getInputStream());
		 TRAINING_DATA = source1.getDataSet();
		 if (TRAINING_DATA.classIndex() == -1)
			 TRAINING_DATA.setClassIndex(TRAINING_DATA.numAttributes() - 1);
		 
		ClassPathResource resource2 = new ClassPathResource(testFile);
		DataSource source2 = new DataSource(resource2.getInputStream());
		TEST_DATA = source2.getDataSet();
		 if (TEST_DATA.classIndex() == -1)
			 TEST_DATA.setClassIndex(TEST_DATA.numAttributes() - 1);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private static void printmenu(){
		System.out.println("Which Algorithm do you want to run ? ");
		System.out.println("");
		System.out.println("Type 0 to exit.");
	    System.out.println("Type 1 for Decision Tree - J48");
	    System.out.println("Type 2 for KNN");
	    System.out.println("Type 3 for SVM");
	    System.out.println("Type 4 for MLP");
	    System.out.println("Type 5 for Boosting Decision Tree");
	    System.out.println("<===========================>");
	}
	
	public static void main(String[] args) {
		//
		if(args == null || args.length == 0 || args[0] == null || args[1] == null) {
			System.out.println("Please specify valid training and test filenames.");
			System.out.println("> java -jar mlprograms-1.0.0.jar <training_file_name> <test_file_name>");
			System.exit(1);
		}
		
		System.out.println("Welcome ML Programs Suite !");
		
		setup(args[0],  args[1]);
		
	    J48Test j48Test = new J48Test();
	    SVMTest svmTest = new SVMTest();
	    KNNTest knnTest = new KNNTest();
	    MLPTest mlpTest = new MLPTest();
	    BoostingTest boostingTest = new BoostingTest();

	    ///
	    
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
			    	System.out.println("Good bye !");
			    	break;
			    }
			    switch (option) {
			    case 1:
			    	//Unpruned Tree
			    	System.out.println("Apply J4.8 algo");
			    	j48Test.runWorkFlow();
			          break;
			    case 2: 
			    	//Pruned Tree
			    	System.out.println("Apply KNN");
			    	knnTest.runWorkFlow();
			          break;
			    case 3:
			    	System.out.println("Apply SVM");
			    	svmTest.runWorkFlow();
			    	break;
			    case 4:   
			    	System.out.println("Apply MLP");
			    	mlpTest.runWorkFlow();
			          break;
			    case 5:   
			    	System.out.println("Apply Boosting");
			    	boostingTest.runWorkFlow();
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
	
}