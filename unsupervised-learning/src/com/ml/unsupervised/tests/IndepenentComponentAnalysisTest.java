package com.ml.unsupervised.tests;

import java.io.File;

import com.ml.unsupervised.loaders.CSVFileReader;

import shared.DataSet;
import shared.Instance;
import shared.filt.IndependentComponentAnalysis;
import util.linalg.Matrix;
import util.linalg.RectangularMatrix;

/**
 * A class for testing
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class IndepenentComponentAnalysisTest {
    
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
        //System.out.println(set);
        IndependentComponentAnalysis filter = new IndependentComponentAnalysis(set, 8);
        filter.filter(set);
        System.out.println("After ICA");
        System.out.println(set.getDescription());
        System.out.println(set);
          
    }

}