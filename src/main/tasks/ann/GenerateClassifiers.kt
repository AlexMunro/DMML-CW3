package tasks.ann

import utils.saveANNClassifier
import weka.classifiers.functions.MultilayerPerceptron

fun generateANNClassifiers(){

    for (epochs in intArrayOf(100, 200)) {

        for (learningRate in doubleArrayOf(0.2, 0.5)) {
            for (hiddenLayers in arrayOf("6", "6,6")) {
                val mlp = MultilayerPerceptron()
                mlp.hiddenLayers = hiddenLayers
                mlp.autoBuild = true
                mlp.trainingTime = epochs
                mlp.learningRate = learningRate
                saveANNClassifier("ANN-$hiddenLayers-$learningRate-$epochs", mlp)
            }
        }
    }
}