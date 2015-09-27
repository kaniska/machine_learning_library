Analysis of Supervised Learning Algorithms

1. What is the goal of the assignment ?
* Here we are evaluating various Supervised Learning algorithms and trying to learn the best hypothesis to classify test samples.

a. The training and test samples are available in  …/903050027/data/
b. In the first problem, we apply the algos to the CreditRating data (mix of numerial, categorical data)
i. We find that ‘Reduce-Error Pruning’ offers best accuracy by effectively removing the noise of the data with a lower memory footprint. 
c. In the second problem, we apply the algos to the Ionoshpere data (all continuous vales)
i. We find that MultiLayerPerceptorn offers best accuracy , though it takes longer time to build models.

2. How to run the algorithms ?
* I have written a set of programs available in the folder …/903050027/algo-analysis-source/
* The main source code : MLProgramSuite.java (package : com.mscourse.MLProgramSuite)
o We can import algo-analysis-source as maven project into Eclipse IDE and run MLProgramSuite.java as a Java application with required arguments.

* The executable  is ‘mlprograms-1.0.0.jar’ located in …/903050027/algo-analysis-cli/ 
* We can run the tool as follows :
o java -jar mlprograms-1.0.0.jar CreditRating-train.arff CreditRating-test.arff
* we can specify any training and test datasets as the arguments.
* java version :  1.7.0_71
* Output of  the program :
o Menu options 
Welcome to ML Programs Suite !

Which Algorithm do you want to run ? 

Type 0 to exit.
Type 1 for Decision Tree - J48
Type 2 for KNN
Type 3 for SVM
Type 4 for MLP
o We can run any algo against any training and test datasets to compare and contrast the accuracy.
o All details are specified in 903050027-analysis.pdf

* I am also using Weka to find the performance metrics and create accuracy graphs.
o Version : weka-3-6-12 , Mac compatible Weka  : weka-3-6-12-oracle-jvm.dmg
o All details are specified in 903050027-analysis.pdf

