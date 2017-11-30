package tasks.preparation

import fer2017Testing
import fer2017Training
import ferHappyTesting
import ferHappyTraining
import saveArff
import weka.attributeSelection.AttributeSelection
import weka.attributeSelection.GainRatioAttributeEval
import weka.attributeSelection.Ranker
import weka.filters.Filter
import weka.filters.unsupervised.attribute.Remove
import java.io.File

/**
 * 1. Convert the above files into arff format and load them to Weka.
 *
 * For convenience, we will save these converted files to the CSV source directory, in future loading only the .arff files
 */

fun task1() {
    val csvDir = File("fer2017/")

    for (csvFile in csvDir.list().filter{Regex(".*csv").matches(it)}){
        println("Converting $csvFile")
        val reader = File("$csvDir/$csvFile").bufferedReader()
        val writer = File("$csvDir/${csvFile.split(".")[0]}.arff").bufferedWriter() // replacing .csv extension

        // Ignoring header and adding it manually
        val lines = reader.lines().skip(1)
        val happy = csvFile.endsWith("happy.csv")

        if (!happy)
            writer.write("@relation fer2017\n" +
                    "@attribute emotion\t{Angry, Disgust, Fear, Happy, Sad, Surprise, Neutral}")
        else
            writer.write("@relation fer2017-happy\n")

        // Adding attributes for each pixel
        for (i in 0 until (48*48)) {
            writer.newLine()
            writer.write("@attribute pixel$i\tNUMERIC")
        }

        if (happy)
            writer.write("\n@attribute is_happy\t{Happy,NotHappy}")

        writer.newLine()
        // Converting number encoded emotions with strings for ease of use
        val emotionMap: Map<Int, String> = mapOf(0 to "Angry", 1 to "Disgust", 2 to "Fear", 3 to "Happy", 4 to "Sad", 5 to "Surprise", 6 to "Neutral")

        // Converting each case
        writer.write("@data")
        writer.newLine()
        for (line in lines){
            // Need to do this conditional because the happy/non-emotion specific cases have attrs in a different order
            if (happy) {
                val rawTokens = line.split(" ").toMutableList()
                val emotion = rawTokens.removeAt(rawTokens.size - 1)
                writer.write(emotion)
                rawTokens.forEach{writer.write(",$it")}
            }
            else {
                val (emotion, pixels) = line.split(",")
                writer.write("${emotionMap[emotion.toInt()]},")
                writer.write(pixels.replace(" ", ","))
            }
            writer.newLine()
        }

        writer.close()
        reader.close()
    }

    filterDatasets()

}

private fun filterDatasets() {
    // Now that the original dataset has been created, we need to prune the attributes for decision trees
    val training = fer2017Training()
    val attributeSelection = AttributeSelection()
    attributeSelection.setEvaluator(GainRatioAttributeEval())
    val search = Ranker()
    search.numToSelect = 10
    attributeSelection.setSearch(search)
    val filter = Remove()
    attributeSelection.SelectAttributes(training)
    filter.setAttributeIndicesArray(attributeSelection.selectedAttributes())
    filter.invertSelection = true
    filter.setInputFormat(training)
    println("Filtering attributes for fer2017-training")
    val filteredTraining = Filter.useFilter(training, filter)
    saveArff("fer2017/filtered/fer2017-training-attributes-filtered.arff", filteredTraining)

    val testing = fer2017Testing()
    testing.setClassIndex(0)

    println("Filtering attributes for fer2017-testing")
    val filteredTesting = Filter.useFilter(testing, filter)
    saveArff("fer2017/filtered/fer2017-testing-attributes-filtered.arff", filteredTesting)

    println("Filtering attributes for fer2017-happy")

    val happyFilter = Remove()
    val happyAttributeSelection = AttributeSelection()
    happyAttributeSelection.setEvaluator(GainRatioAttributeEval())
    happyAttributeSelection.setSearch(search)
    val happyTrainingSet = ferHappyTraining()
    happyAttributeSelection.SelectAttributes(happyTrainingSet)
    happyFilter.setAttributeIndicesArray(happyAttributeSelection.selectedAttributes())
    happyFilter.invertSelection = true
    happyFilter.setInputFormat(happyTrainingSet)
    saveArff("fer2017/filtered/fer2017-training-happy.arff", Filter.useFilter(happyTrainingSet, happyFilter))
    val happyTestingSet = ferHappyTesting()
    saveArff("fer2017/filtered/fer2017-testing-happy.arff", Filter.useFilter(happyTestingSet, happyFilter))
}