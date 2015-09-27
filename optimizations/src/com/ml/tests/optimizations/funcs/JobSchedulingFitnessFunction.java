package com.ml.tests.optimizations.funcs;

import util.linalg.Vector;
import opt.EvaluationFunction;
import shared.Instance;

/**
 * A four peaks evaluation function
 * @author kmandal  kaniska.mandal@gmail.com
 * @version 1.0
 */
public class JobSchedulingFitnessFunction implements EvaluationFunction {
    /**
     * The t value
     */
    private int[][] m_points;
    
    /**
     * Make a new four peaks function
     * @param t the t value
     */
    public JobSchedulingFitnessFunction(int[][] points) {
        this.m_points = points;
    }

    private Instance currentData;
    
   private double min_makespan = 0;
    
    /**
     * @see opt.EvaluationFunction#value(opt.OptimizationData)
     */
    public double value(Instance d) {
        Vector data = d.getData();
        double sum = 0;
        for(int i = 0; i < data.size(); i++) {
        	sum += Math.floor(data.get(i));
        	if(min_makespan > sum ){
        		min_makespan = sum;
        	}
    	}
        if(min_makespan == 0) {
        	min_makespan = sum;
        }
        if(min_makespan == sum) {
        	currentData = d;
        }
    	return Math.abs(min_makespan);
    }
    
    
    public String getBestMachineSchedule(){
    	return currentData.toString();
    }
    
}
