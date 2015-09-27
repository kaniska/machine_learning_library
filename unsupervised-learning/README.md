Randomized Optimizations Example
=======

The codebase uses ABAGAIL to perform Unsupervised ALgo.

Usage
------

For ABAGAIL see [Wiki](https://github.com/pushkar/ABAGAIL/wiki)

Run from Ecplise IDE
-- import the java project
--  run IndepenentComponentAnalysisTest as Java Application

Build Code 
-- include latest ABAGAIL .jar in lib folder
-- run Ant build to generate unsupervised_algo.jar

Run from CLI (mac / linux)
cd unsupervised_learning
1. ICA Test on Marketing Survey dataset
java -cp unsupervised_algo.jar:lib/ABAGAIL.jar com.ml.unsupervised.tests.IndepenentComponentAnalysisTest

2. ICA on Credit Rating dataset and Applying NN Classifier
java -cp unsupervised_algo.jar:lib/ABAGAIL.jar com.ml.unsupervised.tests.NNWithIndependentComponentAnalysisTest

3. ICA on Marketing Survey dataset with KMeans classifier 

java -cp unsupervised_algo.jar:lib/ABAGAIL.jar com.ml.unsupervised.tests.KMeansWithIndependentComponentAnalysisTest

4. NN on KMeans Cluster output

java -cp unsupervised_algo.jar:lib/ABAGAIL.jar com.ml.unsupervised.tests.NNWithKMeansClusterTest

5. EM on ICA 
java -cp unsupervised_algo.jar:lib/ABAGAIL.jar com.ml.unsupervised.tests.EM_ICATest