import weka.core.Instances
import kotlin.coroutines.experimental.buildSequence

/*
 * For tasks 6 and 7, the datasets have to be adjusted such that instances from the testing set are moved to the
 * training set. For ease, we will save these as separate arff files in subdirectories of fer2017/
 */

// Yields elements from one dataset to feed to another
fun removeInstances(dataset: Instances) = buildSequence {
    while (true) {
        yield(dataset.removeAt(dataset.size - 1))
    }
}

fun adjustDatasets(){

    println("Loading datasets")
    val trainingSet: weka.core.Instances = fer2017Training()
    val testingSet = fer2017Testing()
    println("Shifting 3000 instances from testing to training and saving")
    trainingSet.addAll(removeInstances(testingSet).take(3000).toList())
    saveArff("fer2017/shift3000/fer2017-training.arff", trainingSet)
    saveArff("fer2017/shift3000/fer2017-testing.arff", testingSet)
    println("Shifting 6000 instances from testing to training and saving")
    trainingSet.addAll(removeInstances(testingSet).take(3000).toList())
    saveArff("fer2017/shift6000/fer2017-training.arff", trainingSet)
    saveArff("fer2017/shift6000/fer2017-testing.arff", testingSet)

    // Let's do the same for filtered datasets
    val filteredTrainingSet = fer2017TrainingFiltered()
    val filteredTestingSet = fer2017TestingFiltered()

    filteredTrainingSet.addAll(removeInstances(filteredTrainingSet).take(3000).toList())
    saveArff("fer2017/filtered/shift3000/fer2017-training.arff", filteredTrainingSet)
    saveArff("fer2017/filtered/shift3000/fer2017-testing.arff", filteredTestingSet)

    filteredTrainingSet.addAll(removeInstances(filteredTrainingSet).take(3000).toList())
    saveArff("fer2017/filtered/shift6000/fer2017-training.arff", filteredTrainingSet)
    saveArff("fer2017/filtered/shift6000/fer2017-testing.arff", filteredTestingSet)

    println("Adjusted datasets saved")
}