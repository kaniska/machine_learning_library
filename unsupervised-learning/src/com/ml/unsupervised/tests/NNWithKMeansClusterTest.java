package com.ml.unsupervised.tests;

import dist.*;
import opt.*;
import opt.example.*;
import opt.ga.*;
import shared.*;
import shared.filt.PrincipalComponentAnalysis;
import util.linalg.DenseVector;
import func.KMeansClusterer;
import func.nn.backprop.*;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import java.text.*;

import com.ml.unsupervised.loaders.CSVFileReader;

/**
 * Apply NN Classifier on output of KMeans Cluster
 * @version 1.0
 */
public class NNWithKMeansClusterTest {

    private static int inputLayer = 7, hiddenLayer = 5, outputLayer = 1, trainingIterations = 100;
    private static BackPropagationNetworkFactory factory = new BackPropagationNetworkFactory();

    private static BackPropagationNetwork network = new BackPropagationNetwork();

    public static void main(String[] args) {
    	try {
    		String fileName = new File("").getAbsolutePath() + "/resources/credit_rating.csv";
       	 BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            List<Instance> data = new ArrayList<Instance>();
            Pattern pattern = Pattern.compile("[ ,]+");

            while ((line = br.readLine()) != null) {
                String[] split = pattern.split(line.trim());
                double[] input = new double[split.length];
                for (int i = 0; i < split.length-1; i++) {
                    input[i] = Double.parseDouble(split[i]);
                }
                
                Instance label = new Instance(Double.parseDouble(split[split.length-1]));
                Instance instance = new Instance(new DenseVector(input), label);
                data.add(instance);
            }
            br.close();
            Instance[] instances = (Instance[]) data.toArray(new Instance[0]);
            DataSet set = new DataSet(instances);
            set.setDescription(new DataSetDescription(set));
            
            //////////////////
            
            System.out.println("Before KMeans");
            System.out.println(set.getDescription());
            
            KMeansClusterer km = new KMeansClusterer(3);
            km.estimate(set);
            
            System.out.println("After KMeans");
            System.out.println(set.getDescription());
            
            /////
            network = factory.createClassificationNetwork(
                    new int[] {inputLayer, hiddenLayer, outputLayer});
            
            ConvergenceTrainer trainer = new ConvergenceTrainer(
                    new BatchBackPropagationTrainer(set, network,
                        new SumOfSquaresError(), new RPROPUpdateRule()));
             trainer.train();
             System.out.println("Convergence in " 
                 + trainer.getIterations() + " iterations");
             
             Instance[] patterns = set.getInstances();
             
             int true_positives_num = 0;
             int actual_class =  0;
             int predicted_class = 0;	
             
             for (int i = 0; i < patterns.length; i++) {
                 network.setInputValues(patterns[i].getData());
                 network.run();
                 //System.out.println("~~");
                 actual_class = Math.round(Float.parseFloat(patterns[i].getLabel().toString()));
                 predicted_class = Math.round(Float.parseFloat(network.getOutputValues().toString()));
                 if(actual_class == predicted_class) {
                	 true_positives_num++;
                 }
             }
             
             double true_positives = ((true_positives_num*100/(patterns.length)));
             System.out.println("True Positives % "+true_positives);

    	}catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
    }
    
}
