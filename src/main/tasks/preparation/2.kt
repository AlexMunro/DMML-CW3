package tasks.preparation

import java.io.File

/**
 * 2. Create folders on your computer to store classifiers, screenshots and results of all your experiments
 *
 * Execute to create folders. Will ignore any folders that already exist.
 */
fun task2(){

    // First we need a directory for storing results
    File("results").mkdir()

    // Within there, one for each of the two parts
    val dt = File("results/dt")
    dt.mkdir()
    val ann = File("results/ann")
    ann.mkdir()

    // Then make the task specific folders for each part
    for (dir in listOf(dt, ann)){
        for (t in 3..8){
            File("$dir/task$t").mkdir()
        }
        // And also one for any manual results
        File("$dir/manual").mkdir()
    }

    // Separate from results, a directory for classifiers will also be useful
    File("classifiers").mkdir()
    File("classifiers/dt").mkdir()
    File("classifiers/ann").mkdir()

    // Creating separate directories for classifiers with modified training/testing sets
    File("classifiers/dt/").mkdir()

    // For tasks 6 and 7, we need to adjust the training and testing sets. For neatness, let's have directories!
    File("fer2017/shift3000").mkdir() // Task 6
    File("fer2017/shift6000").mkdir() // Task 7

    // Creating a directory for manually filtered datasets
    File("fer2017/filtered").mkdir()
    File("fer2017/filtered/shift3000").mkdir()
    File("fer2017/filtered/shift6000").mkdir()

    // Creating directories to additionally run non cross-folded
    File("results/dt/task6/test").mkdir()
    File("results/dt/task7/test").mkdir()
    File("results/ann/task6/test").mkdir()
    File("results/ann/task7/test").mkdir()

    // Directory for DT happy comparison
    File("results/dt/task3/happy").mkdir()
    File("results/dt/task5/happy").mkdir()

    // Finally, a place to keep screenshots
    File("screenshots").mkdir()
}
