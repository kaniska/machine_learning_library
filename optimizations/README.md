Randomized Optimizations Example
=======

The codebase uses ABAGAIL to perform Randomized Optimized Tests using Simulated Annealing, Randomized Hill Climbing, Genetic Programming.

Usage
------

For ABAGAIL see [Wiki](https://github.com/pushkar/ABAGAIL/wiki)

Run from Ecplise IDE
-- import the java project
-- change the params in  CreditRatingEvalutaion.java
--  run CreditRatingEvalutaion as Java Application

Build code 
-- include latest ABAGAIL .jar in lib folder
-- run Ant build to generate randomized_optimizations.jar

Run from CLI (mac / linux)
1. Local Random Search Algo
java -cp randomized_optimizations.jar:lib/ABAGAIL.jar com.ml.tests.optimizations.CreditRatingTest
2. 
java -cp randomized_optimizations.jar:lib/ABAGAIL.jar com.ml.tests.optimizations.NQueensTest
3.
java -cp randomized_optimizations.jar:lib/ABAGAIL.jar com.ml.tests.optimizations.CartCenteringTest
4.
java -cp randomized_optimizations.jar:lib/ABAGAIL.jar com.ml.tests.optimizations.JobSchedulingTest