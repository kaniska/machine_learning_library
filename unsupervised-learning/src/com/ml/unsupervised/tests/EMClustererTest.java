package com.ml.unsupervised.tests;

import java.io.File;

import com.ml.unsupervised.loaders.CSVFileReader;

import dist.Distribution;
import dist.MultivariateGaussian;
import func.EMClusterer;
import shared.DataSet;
import shared.Instance;
import util.linalg.DenseVector;
import util.linalg.RectangularMatrix;

/**
 * Testing
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class EMClustererTest {
    /**
     * The test main
     * @param args ignored
     */
    public static void main(String[] args) throws Exception {
        //Instance[] instances = new Instance[100];
        CSVFileReader dsr = new CSVFileReader();
        boolean skipHeader = true;
        DataSet ds = dsr.read(new File("").getAbsolutePath() + "/resources/marketing_survey.csv", skipHeader);
        
        EMClusterer em = new EMClusterer();
        em.estimate(ds);
        System.out.println(em);
    }
}
