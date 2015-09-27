package com.ml.tests.optimizations;

import java.util.Arrays;
import java.util.Random;

import com.ml.tests.optimizations.funcs.CartCenteringFitnessFunction;

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
public class CartCenteringTest {
    /** The n value */
    private static final int N = 20;
    /**
     * The test main
     * @param args ignored
     */
    public static void main(String[] args) {
        Random random = new Random(10);
        // create the random velocity
        int[] velocityData = new int[N];
        for (int i = 0; i < N; i++) {
            int velocity = random.nextInt(10);
            velocityData[i] = velocity;
        }
        // for rhc, sa, and ga we use a permutation based encoding
        CartCenteringFitnessFunction ef = new CartCenteringFitnessFunction();
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
        FixedIterationTrainer fit = new FixedIterationTrainer(rhc, 2000);
        fit.train();
        System.out.println("RHC: " + ef.value(rhc.getOptimal()));
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
       // System.out.println(ef.getCurrentCartVelocityPosition());
        
        System.out.println("============================");
        
        starttime = System.currentTimeMillis();
        SimulatedAnnealing sa = new SimulatedAnnealing(1E12, .01, hcp);
        fit = new FixedIterationTrainer(sa, 2000);
        fit.train();
        System.out.println("SA: " + ef.value(sa.getOptimal()));
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
       // System.out.println(ef.getCurrentCartVelocityPosition());
        
        System.out.println("============================");
        
        starttime = System.currentTimeMillis();
        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(200, 10, 60, gap);
        fit = new FixedIterationTrainer(ga, 1000);
        fit.train();
        System.out.println("GA: " + ef.value(ga.getOptimal()));
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
       // System.out.println(ef.getCurrentCartVelocityPosition());
        
        System.out.println("============================");
        
        starttime = System.currentTimeMillis();
        MIMIC mimic = new MIMIC(200, 10, pop);
        fit = new FixedIterationTrainer(mimic, 10);
        fit.train();
        System.out.println("MIMIC: " + ef.value(mimic.getOptimal())); 
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
       /// System.out.println("MIMIC: Optimal Schedule: ");
       // System.out.println(ef.getCurrentCartVelocityPosition());
    }
}
