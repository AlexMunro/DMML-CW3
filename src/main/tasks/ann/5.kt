package tasks.ann

import fer2017TestingFiltered
import fer2017TrainingFiltered
import utils.annClassifiers
import utils.runTest

/**
 * 5. Repeat steps 3 and 4, this time using testing data set instead of Weka’s cross validation
 * */

fun task5(){

    val classifiers = annClassifiers()
    val trainingSet = fer2017TrainingFiltered()
    val testingSet = fer2017TestingFiltered()

    for (cls in classifiers){
        println("Running test set on $cls")
        cls.value.buildClassifier(trainingSet)
        runTest(cls.value, testingSet, "results/ann/task5/${cls.key}")
    }

}
