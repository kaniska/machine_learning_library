package com.ml.unsupervised.tests;

import java.io.File;

import com.ml.unsupervised.loaders.CSVFileReader;

import dist.Distribution;
import dist.MultivariateGaussian;
import func.KMeansClusterer;
import shared.DataSet;
import shared.Instance;
import shared.filt.IndependentComponentAnalysis;
import shared.filt.InsignificantComponentAnalysis;
import shared.reader.CSVDataSetReader;
import shared.reader.DataSetReader;
import util.linalg.DenseVector;
import util.linalg.RectangularMatrix;

/**
 * Testing
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class KMeansWithInsignificantComponentAnalysisTest {
    /**
     * The test main
     * @param args ignored
     */
    public static void main(String[] args) throws Exception {
       // Instance[] instances = new Instance[100];
        
        CSVFileReader dsr = new CSVFileReader();
        boolean skipHeader = true;
        DataSet set = dsr.read(new File("").getAbsolutePath() + "/resources/marketing_survey.csv", skipHeader);
        
        System.out.println("Before ICA");
        System.out.println(set.getDescription());
        
        //System.out.println(set);
        InsignificantComponentAnalysis filter = new InsignificantComponentAnalysis(set);
        filter.filter(set);
        System.out.println("After ICA");
        System.out.println(set.getDescription());
        
        KMeansClusterer km = new KMeansClusterer(4);
        km.estimate(set);
        System.out.println("After KMeans");
        System.out.println(set.getDescription());
        System.out.println(km);
    }
}
