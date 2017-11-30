import weka.core.Instances

/**
 * Utils relating to the FER datasets
 */

// Load functions
fun fer2017Training(): Instances {
    return loadArff("fer2017/fer2017-training.arff")
}

fun fer2017TrainingFiltered(): Instances {
    return loadArff("fer2017/filtered/fer2017-training-attributes-filtered.arff")
}

fun fer2017Testing(): Instances {
    return loadArff("fer2017/fer2017-testing.arff")
}

fun fer2017TestingFiltered(): Instances{
    return loadArff("fer2017/filtered/fer2017-testing-attributes-filtered.arff")
}

fun happyTraining(): Instances {
    return loadArff("fer2017/fer2017-training-happy.arff")
}

fun happyTesting(): Instances {
    return loadArff("fer2017/fer2017-testing-happy.arff")
}

fun fer2017Training3000(): Instances {
    return loadArff("fer2017/shift3000/fer2017-training.arff")
}

fun fer2017Training3000Filtered(): Instances{
    return loadArff("fer2017/filtered/shift3000/fer2017-training.arff")
}

fun fer2017Testing3000(): Instances {
    return loadArff("fer2017/shift3000/fer2017-testing.arff")
}

fun fer2017Testing3000Filtered(): Instances {
    return loadArff("fer2017/filtered/shift3000/fer2017-testing.arff")
}

fun fer2017Training6000(): Instances {
    return loadArff("fer2017/shift6000/fer2017-training.arff")
}

fun fer2017Training6000Filtered(): Instances {
    return loadArff("fer2017/filtered/shift6000/fer2017-training.arff")
}

fun fer2017Testing6000(): Instances {
    return loadArff("fer2017/shift6000/fer2017-testing.arff")
}

fun fer2017Testing6000Filtered(): Instances {
    return loadArff("fer2017/filtered/shift6000/fer2017-testing.arff")
}

fun ferHappyTraining(): Instances{
    return loadArff("fer2017/fer2017-training-happy.arff")
}

fun ferHappyTesting(): Instances{
    return loadArff("fer2017/fer2017-testing-happy.arff")
}

fun ferHappyTrainingFiltered(): Instances{
    return loadArff("fer2017/filtered/fer2017-training-happy.arff")
}

fun ferHappyTestingFiltered(): Instances{
    return loadArff("fer2017/filtered/fer2017-testing-happy.arff")
}