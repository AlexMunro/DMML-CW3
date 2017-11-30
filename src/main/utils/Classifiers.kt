package utils

import weka.classifiers.Classifier
import weka.core.SerializationHelper
import java.io.*

/**
 * Serialises and deserialises classifiers per https://weka.wikispaces.com/Serialization
 */

fun loadClassifier(source: String): Classifier {
    return SerializationHelper.read(source) as Classifier
}

// Simplifying saving to specific dirs since this will happen outside of this file
fun saveDTClassifier(name: String, classifier: Classifier){
    SerializationHelper.write("classifiers/dt/$name", classifier)
}

fun saveANNClassifier(name: String, classifier: Classifier){
    SerializationHelper.write("classifiers/ann/$name", classifier)
}

// Functions for loading all classifiers at once, intended for repeating experiments over a type of classifier

fun dtClassifiers(): Map<String, Classifier> {
    val map = HashMap<String, Classifier>()
    val dir = File("classifiers/dt")
    dir.list().forEach {
        map.put(it, loadClassifier("$dir/$it"))
    }
    return map
}

fun annClassifiers(): Map<String, Classifier>{
    val map = HashMap<String, Classifier>()
    val dir = File("classifiers/ann")
    dir.list().forEach {
        map.put(it, loadClassifier("$dir/$it"))
    }
    return map
}