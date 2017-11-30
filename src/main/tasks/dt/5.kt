package tasks.dt

import fer2017TestingFiltered
import fer2017TrainingFiltered
import ferHappyTestingFiltered
import ferHappyTrainingFiltered
import utils.dtClassifiers
import utils.runCrossValidation
import utils.runTest

/**
 * 5. Repeat steps 3 and 4, this time using testing data set instead of Weka’s cross validation
 * */

fun task5(){

    val classifiers = dtClassifiers()
    val trainSet = fer2017TrainingFiltered()
    val testSet = fer2017TestingFiltered()
    val happyDataset = ferHappyTrainingFiltered()
    val happyTestSet = ferHappyTestingFiltered()

    for (cls in classifiers){
        println("Running test set on $cls")
        cls.value.buildClassifier(trainSet)
        runTest(cls.value, testSet, "results/dt/task5/${cls.key}")// Also running for just the happy set
        cls.value.buildClassifier(happyDataset)
        runTest(cls.value, happyTestSet, "results/dt/task5/happy/${cls.key}")
    }

}
