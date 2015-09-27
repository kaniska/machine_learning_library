package com.ml.tests.optimizations;

import java.util.Arrays;
import java.util.Random;

import com.ml.tests.optimizations.funcs.CartCenteringFitnessFunction;
import com.ml.tests.optimizations.funcs.JobSchedulingFitnessFunction;

import dist.DiscreteDependencyTree;
import dist.DiscretePermutationDistribution;
import dist.DiscreteUniformDistribution;
import dist.Distribution;
import opt.DiscreteChangeOneNeighbor;
import opt.EvaluationFunction;
import opt.SwapNeighbor;
import opt.GenericHillClimbingProblem;
import opt.HillClimbingProblem;
import opt.NeighborFunction;
import opt.RandomizedHillClimbing;
import opt.SimulatedAnnealing;
import opt.ga.CrossoverFunction;
import opt.ga.DiscreteChangeOneMutation;
import opt.ga.SingleCrossOver;
import opt.ga.SwapMutation;
import opt.ga.GenericGeneticAlgorithmProblem;
import opt.ga.GeneticAlgorithmProblem;
import opt.ga.MutationFunction;
import opt.ga.StandardGeneticAlgorithm;
import opt.ga.UniformCrossOver;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;

/**
 * 
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class JobSchedulingTest {
    /** The n value */
    private static final int N = 5;
    /**
     * The test main
     * @param args ignored
     */
    public static void main(String[] args) {
        Random random1 = new Random(20);
        Random random2 = new Random(20);
        Random random3 = new Random(20);
        // create the random points
        int[][] scheduleTable = new int[N][3];
        for (int i = 0; i < scheduleTable.length; i++) {
            int job1_time = random1.nextInt();
    		int job2_time = random2.nextInt();
    		int job3_time = random3.nextInt();
    		scheduleTable[i][0] = job1_time;
    		scheduleTable[i][1] = job2_time;
    		scheduleTable[i][2] = job3_time;
        }
        // for rhc, sa, and ga we use a permutation based encoding
        JobSchedulingFitnessFunction ef = new JobSchedulingFitnessFunction(scheduleTable);
        Distribution odd = new DiscretePermutationDistribution(N);
        NeighborFunction nf = new SwapNeighbor();
        MutationFunction mf = new SwapMutation();
        CrossoverFunction cf = new SingleCrossOver();
        HillClimbingProblem hcp = new GenericHillClimbingProblem(ef, odd, nf);
        GeneticAlgorithmProblem gap = new GenericGeneticAlgorithmProblem(ef, odd, mf, cf);
        
        Distribution df = new DiscreteDependencyTree(.1); 
        ProbabilisticOptimizationProblem pop = new GenericProbabilisticOptimizationProblem(ef, odd, df);
        
        long starttime = System.currentTimeMillis();
        RandomizedHillClimbing rhc = new RandomizedHillClimbing(hcp);      
        FixedIterationTrainer fit = new FixedIterationTrainer(rhc, 10);
        fit.train();
        System.out.println("RHC: " + ef.value(rhc.getOptimal()));
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
        //System.out.println("RHC: Optimal Schedule: ");
       // System.out.println(ef.getBestMachineSchedule());
        
        System.out.println("============================");
        
        starttime = System.currentTimeMillis();
        SimulatedAnnealing sa = new SimulatedAnnealing(1E82, .5, hcp);
        fit = new FixedIterationTrainer(sa, 10);
        fit.train();
        System.out.println("SA: " + ef.value(sa.getOptimal()));
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
       // System.out.println("SA: Optimal Schedule: ");
       // System.out.println(ef.getBestMachineSchedule());
        
        System.out.println("============================");
        
        starttime = System.currentTimeMillis();
        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(200, 100, 20, gap);
        fit = new FixedIterationTrainer(ga, 10);
        fit.train();
        System.out.println("GA: " + ef.value(ga.getOptimal()));
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
       // System.out.println("GA: Optimal Schedule: ");
       // System.out.println(ef.getBestMachineSchedule());
        
        System.out.println("============================");
        
        starttime = System.currentTimeMillis();
        MIMIC mimic = new MIMIC(200, 100, pop);
        fit = new FixedIterationTrainer(mimic, 10);
        fit.train();
        System.out.println("MIMIC: " + ef.value(mimic.getOptimal())); 
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
       // System.out.println("MIMIC: Optimal Schedule: ");
       // System.out.println(ef.getBestMachineSchedule());
    }
}
