package tasks.dt

/**
 * 7. Make new training and testing sets again, this time with 6000 instances in the training set,
 * and the remainder in the test set, and again repeat steps 3 and 4.
 */

import fer2017Testing6000Filtered
import fer2017Training6000Filtered
import utils.dtClassifiers
import utils.runCrossValidation
import utils.runTest

fun task7(){

    val classifiers = dtClassifiers()
    val dataset = fer2017Training6000Filtered()
    val testset = fer2017Testing6000Filtered()

    for (cls in classifiers){
        println("Running cross-validation on $cls")
        runCrossValidation(cls.value, dataset, "results/dt/task7/${cls.key}")

        // Now let's also run against the resultant testing set
        cls.value.buildClassifier(dataset)
        runTest(cls.value, testset, "results/dt/task7/test/${cls.key}")
    }

}
