package utils

import weka.classifiers.Classifier
import weka.classifiers.Evaluation
import weka.core.Instances
import java.io.File
import java.util.*

/*
 * Provides functions to execute and save the results of a classification experiment
 */

fun runCrossValidation(classifier: Classifier, trainingSet: Instances, outFile: String){
    val eval = Evaluation(trainingSet)
    eval.crossValidateModel(classifier, trainingSet, 10, Random())
    saveEvaluation(eval,outFile)
}

fun runTest(classifier: Classifier, testingSet: Instances, outFile: String){
    val eval = Evaluation(testingSet)
    eval.evaluateModel(classifier, testingSet)
    saveEvaluation(eval,outFile)
}

fun saveEvaluation(eval: Evaluation, outFile: String){
    val writer = File(outFile).bufferedWriter()
    writer.write(eval.toSummaryString(true))
    writer.newLine()
    writer.write(eval.toClassDetailsString())
    writer.newLine()
    writer.write(eval.toMatrixString())
    writer.close()
}