package com.mscourse.svm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.core.io.ClassPathResource;
import com.mscourse.MLProgramSuite;
import com.mscourse.algo.AlgoTest;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.NormalizedPolyKernel;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * 
 * @author kmanda1
 *
 */
public class SVMTest extends AlgoTest{
	
	private static void printmenu(){
		System.out.println("Type 0 - to exit");
	    System.out.println("Type 1 - to run SVM with PolyKernel.");
	    System.out.println("Type 2 - to run SVM with Normalized Kernel.");
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
			    	System.out.println("Type 1 - to run SVM with PolyKernel.");
			    	testSVMwithPolyKernel();
			          break;
			    case 2: 
			    	//Pruned Tree
			    	System.out.println("Type 2 - to run SVM with Normalized Kernel.");
			    	testSVMwithNormalizedPolyKernel();
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

	
	public void testSVMwithNormalizedPolyKernel(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			SMO smo = new SMO();
			NormalizedPolyKernel normalizedPolyKernel = new NormalizedPolyKernel();
			smo.setKernel(normalizedPolyKernel);
			smo.setC(2);
			smo.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(smo.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(smo, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(smo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testSVMwithPolyKernel(){
		try {
			// Train an unpruned C4.5 Algo
			String[] options = new String[1];
			SMO smo = new SMO();
			smo.buildClassifier(MLProgramSuite.TRAINING_DATA);
			System.out.println(smo.toString());
			//
			Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			eval.evaluateModel(smo, MLProgramSuite.TRAINING_DATA);
			System.out.println(eval.toSummaryString("\nResults\n======\n", true));
			//
			System.out.println("---- Now apply the Classifier to the test data --- ");
			applyAlgoToTestData(smo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}