package tasks.dt

import utils.saveDTClassifier
import weka.classifiers.trees.J48
import weka.classifiers.trees.REPTree

/**
 * Before proceeding with the numbered tasks in this directory, we have to create the classifiers
 *
 * For DTs, we will investigate three implementations:
 *      1) J48
 *      2) User Classifier
 *      3) <BONUS SECRET EXTRA DECISION TREE>
 *
 * Additionally, we should
 */

fun generateDTClassifiers(){

    // Generate J48 classifiers

    for (reducedErrorPruning in booleanArrayOf(false,true)){
        for (minNumObj in intArrayOf(2, 5, 10)){
            for (useSplits in booleanArrayOf(false, true)){
                val j48 = J48()
                j48.binarySplits = useSplits
                j48.reducedErrorPruning = reducedErrorPruning
                j48.minNumObj = minNumObj
                saveDTClassifier("J48-$useSplits-$reducedErrorPruning-$minNumObj", j48)
            }
            val rep = REPTree()
            rep.minNum = minNumObj.toDouble()
            rep.noPruning = reducedErrorPruning
            saveDTClassifier("REP-$reducedErrorPruning-$minNumObj", rep)
        }
    }

}