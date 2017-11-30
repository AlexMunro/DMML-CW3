package tasks.dt

import fer2017TrainingFiltered
import ferHappyTrainingFiltered
import utils.dtClassifiers
import utils.runCrossValidation

/**
 * 3. Using the provided data set, and Weka’s facility for 10-fold cross validation, run the classifier, and
 *note its accuracy for varying learning parameters provided by Weka. (Below you will find more
 * instructions on those.) Record all your findings and explain them. Make sure you understand and can
 * explain logically the meaning of the confusion matrix, as well as the information contained in the
 * “Detailed Accuracy” field: TP Rate, FP Rate, Precision, Recall, F Measure, ROC Area.
 * */

fun task3(){

    val classifiers = dtClassifiers()
    val dataset = fer2017TrainingFiltered()
    val happyDataset = ferHappyTrainingFiltered()

    for (cls in classifiers){
        println("Running cross-validation on $cls")
        runCrossValidation(cls.value, dataset, "results/dt/task3/${cls.key}")
        // Also running for just the happy set
        runCrossValidation(cls.value, happyDataset, "results/dt/task3/happy/${cls.key}")
    }

}
