package com.ml.unsupervised.tests;

import java.io.File;

import com.ml.unsupervised.loaders.CSVFileReader;

import shared.DataSet;
import shared.Instance;
import shared.filt.PrincipalComponentAnalysis;
import util.linalg.Matrix;

/**
 * A class for testing
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class PrincipalComponentAnalysisTest {
    
    /**
     * The test main
     * @param args ignored
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        CSVFileReader dsr = new CSVFileReader();
        boolean skipHeader = true;
        DataSet set = dsr.read(new File("").getAbsolutePath() + "/resources/marketing_survey.csv", skipHeader);
 
        System.out.println("Before PCA");
        System.out.println(set.getDescription());
       // System.out.println(set);
        
        //PrincipalComponentAnalysis filter1 = new PrincipalComponentAnalysis(set);
        // System.out.println(filter.getProjection().transpose());
        // filter1.filter(set);
        // System.out.println("After PCA");
        // System.out.println(set.getDescription());
        
        PrincipalComponentAnalysis filter2 = new PrincipalComponentAnalysis(set);
       // System.out.println(filter.getProjection().transpose());
        filter2.filter(set);
        System.out.println("After PCA - Select few features ");
        System.out.println(set.getDescription());
        System.out.println(filter2.getProjection());
        Matrix reverse = filter2.getProjection().transpose();
        for (int i = 0; i < set.size(); i++) {
            Instance instance = set.get(i);
            instance.setData(reverse.times(instance.getData()).plus(filter2.getMean()));
        }
        // System.out.println("After reconstructing");
        // System.out.println(set);
        
    }

}
