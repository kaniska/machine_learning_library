package com.ml.unsupervised.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ml.unsupervised.loaders.CSVFileReader;

import shared.DataSet;
import shared.DataSetDescription;
import shared.Instance;
import shared.filt.LinearDiscriminantAnalysis;
import util.linalg.DenseVector;

/**
 * A class for testing
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class LinearDiscriminantAnalysisTest {
    
    /**
     * The test main
     * @param args ignored
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
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
    	
        /////////// 
         
        System.out.println("Before LDA");
        System.out.println(set.getDescription());
        LinearDiscriminantAnalysis filter = new LinearDiscriminantAnalysis(set);
        filter.filter(set);
        System.out.println("After LDA");
       //  System.out.println(filter.getProjection());
        // System.out.println(set);
       // filter.reverse(set);
       // System.out.println("After reconstructing");
       // System.out.println(set.getDescription());
        
    }

}
