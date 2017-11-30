import weka.core.Instances
import weka.core.converters.ArffLoader
import weka.core.converters.ArffSaver
import weka.core.stopwords.Null
import java.io.File

/**
 Util functions for ARFF file handling - largely the same as in CW2
 */

/**
 * Loads an arff datamodel from file
 * @param source relative path of the source arff file
 * @return Weka Instances file of the dataset to load
 */
fun loadArff(source: String): Instances {
    val loader = ArffLoader()
    loader.setFile(File(source))
    val dataSet = loader.dataSet
    try {
        dataSet.setClass(dataSet.attribute("emotion"))
    } catch(e: NullPointerException){
        // I don't see a way to check for attribute existence so catching NPE instead
        dataSet.setClass(dataSet.attribute("is_happy"))
    }
    return dataSet
}

/**
 * Saves an arff model to file
 * @param outFile relative path of destination file (including extension)
 * @param dataset instances file
 */
fun saveArff(outFile: String, dataset: Instances){
    val saver = ArffSaver()
    saver.setFile(File(outFile))
    saver.instances = dataset
    saver.writeBatch()
}