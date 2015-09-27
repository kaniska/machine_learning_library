package com.ml.tests.optimizations.funcs;

import util.linalg.Vector;
import opt.EvaluationFunction;
import shared.Instance;

/**
 * A four peaks evaluation function
 * @author kmandal  kaniska.mandal@gmail.com
 * @version 1.0
 */
public class CartCenteringFitnessFunction implements EvaluationFunction {
    
    private Instance currentData;

    /**
     * @see opt.EvaluationFunction#value(opt.OptimizationData)
     */
    public double value(Instance d) {
        Vector data = d.getData();
        double sum = 0;
        for(int i = 0; i < data.size()-1; i++) {
    		double velocity = 1.5 * data.get(i)- 0.75;
    		double time = 0;
    		while(time++ < 10 && (Math.abs(velocity) > 0.01)) {
    			int thrust = 1;
    			if(Math.abs(velocity) > 0 ) {
    				thrust = -1;
    			}
    			velocity += thrust * 0.02;
    		}
    		sum += time;
    	}
        currentData = d;
    	return Math.abs(sum);
    }
    
    
    public String getCurrentCartVelocityPosition(){
    	return currentData.toString();
    }
    
}
