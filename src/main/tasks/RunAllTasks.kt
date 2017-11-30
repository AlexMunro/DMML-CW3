package tasks

import adjustDatasets
import tasks.ann.generateANNClassifiers
import tasks.dt.generateDTClassifiers
import tasks.preparation.task1
import tasks.preparation.task2

import tasks.dt.task3 as dt3
import tasks.dt.task5 as dt5
import tasks.dt.task6 as dt6
import tasks.dt.task7 as dt7

import tasks.ann.task3 as ann3
import tasks.ann.task5 as ann5
import tasks.ann.task6 as ann6
import tasks.ann.task7 as ann7

/**
 * Since the path for running the complete set of experiments is a bit more complex for this assignment,
 * everything is executed in the correct order in this main. You should be able to get complete results from running
 * this with only the source code, the manually generated classifiers and the provided files in fer2017/
 *
 * Run this with a high Xmx, these datasets are big!
 **/

fun main(args: Array<String>){
    // Data preparation tasks
    task1()
    task2()

    // Generate classifiers
    generateDTClassifiers()
    generateANNClassifiers()

    // Run experiments for each classifier type
    dt3()
    dt5()
    ann3()
    ann5()

    // Preparing data for tasks 6 and 7
    adjustDatasets()

    // Running experiments with adjusted datasets
    dt6()
    dt7()
    ann6()
    ann7()
}