/**
 * 
 */
package com.mscourse.algo;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;

import com.mscourse.MLProgramSuite;

/**
 * @author kmanda1
 *
 */
public abstract class AlgoTest {
	
	protected void applyAlgoToTestData(Classifier classifier){
		try {
			 Evaluation eval = new Evaluation(MLProgramSuite.TRAINING_DATA);
			 eval.evaluateModel(classifier, MLProgramSuite.TEST_DATA); 
			 System.out.println(eval.toSummaryString("\nResults\n\n", false));
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
