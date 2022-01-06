package digitAi

import Images.*
import IO.*

class RandomSmart(timeInMillis:Long): 
    val numberOfTest = 2_000
    val t1 = System.currentTimeMillis
    var bestNuralNetwork = NuralNetwork.random(Seq(50,20,20))


    while t1 > System.currentTimeMillis - timeInMillis do
        val currantNetwork = bestNuralNetwork.ramdomized(5)
        for i <- 1 to numberOfTest do 
            currantNetwork(answer(i), imagesDouble(i))
        if currantNetwork.score > bestNuralNetwork.score then 
            bestNuralNetwork = currantNetwork

            print(System.currentTimeMillis - t1)
            print("\t")
            println(bestNuralNetwork.score / numberOfTest) 

            //TODO
            svaveString(bestNuralNetwork)



