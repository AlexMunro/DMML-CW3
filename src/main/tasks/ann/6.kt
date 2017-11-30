package tasks.ann

import fer2017Testing3000Filtered
import fer2017Training3000Filtered
import utils.annClassifiers
import utils.runCrossValidation
import utils.runTest

fun task6(){

    val classifiers = annClassifiers()
    val dataset = fer2017Training3000Filtered()
    val testset = fer2017Testing3000Filtered()

    for (cls in classifiers){
        println("Running cross-validation on ${cls.key}")
        runCrossValidation(cls.value, dataset, "results/ann/task6/${cls.key}")

        // Now let's also run against the resultant testing set
        cls.value.buildClassifier(dataset)
        runTest(cls.value, testset, "results/ann/task6/test/${cls.key}")

    }

}
