package com.ml.unsupervised.tests;

import java.io.File;

import com.ml.unsupervised.loaders.CSVFileReader;

import shared.DataSet;
import shared.Instance;
import shared.filt.InsignificantComponentAnalysis;
import util.linalg.Matrix;

/**
 * A class for testing
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class InsignificantComponentAnalysisTest {
    
    /**
     * The test main
     * @param args ignored
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        CSVFileReader dsr = new CSVFileReader();
        boolean skipHeader = true;
        DataSet set = dsr.read(new File("").getAbsolutePath() + "/resources/marketing_survey.csv", skipHeader);
        System.out.println("Before ICA");
        System.out.println(set.getDescription());
        
        InsignificantComponentAnalysis filter = new  InsignificantComponentAnalysis(set, 12);
        //System.out.println(filter.getEigenValues());
        //System.out.println(filter.getProjection().transpose());
        filter.filter(set);
        System.out.println("After ICA");
        System.out.println(set.getDescription());
       // System.out.println(filter.getProjection().transpose());
       // Matrix reverse = filter.getProjection().transpose();
       // for (int i = 0; i < set.size(); i++) {
       //     Instance instance = set.get(i);
       //     instance.setData(reverse.times(instance.getData()).plus(filter.getMean()));
       // }
       // System.out.println("After reconstructing");
       // System.out.println(set);
        
    }

}
