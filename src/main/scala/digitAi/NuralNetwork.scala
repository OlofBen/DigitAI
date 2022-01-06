package digitAi

class NuralNetwork(val weights:Seq[Matrix],val biases:Seq[Matrix]):

    var score:Double = 0 

    def apply(rightAnswer:Int, vector:Array[Double]):Unit = 
        var node:Matrix = Matrix(Array(vector))
        for i <- weights.indices do 
            node = node * weights(i) + biases(i)
            node = node.limited

        evaluate(rightAnswer,(for i <- 0 to 9 yield node(0)(i)))


    def outcome(answer:Seq[Double]) = 
        var highest = answer(0)
        var out = 0
        for i <- answer.indices do 
            if answer(i) > highest then 
                highest = answer(i)
                out = i 
        out
    
    def evaluate(rightAnswer:Int, finalNodes:Seq[Double]):Unit = 
        val sum = finalNodes.sum
        val normalized = finalNodes.map(_/sum)
        val right = normalized(rightAnswer)
        score += right

    def ramdomized(range:Double):NuralNetwork = 
        val newWeights = weights.map(_.makeNewRandom(range))
        val newBiases = biases.map(_.makeNewRandom(range))
        NuralNetwork(newWeights, newBiases)

    def saveToFile():String = 
        var out = s"${weights.length},${biases.length}\n"
        out += weights.map(_.saveToFile()).mkString("\n")
        out += biases.map(_.saveToFile()).mkString("\n")
        out



   


object NuralNetwork:
    def empty(nodesInMiddle:Seq[Int]): NuralNetwork = 
        val numberOfNodes:Seq[Int] = 784 +: nodesInMiddle :+ 10 

        val weights:Seq[Matrix] = for i <- numberOfNodes.indices.dropRight(1) yield 
            Matrix.matrixOfSize(numberOfNodes(i),numberOfNodes(i+1))

        val biases:Seq[Matrix] = for i <- numberOfNodes.indices.drop(1) yield 
            Matrix.matrixOfSize(1, numberOfNodes(i))

        new NuralNetwork(weights, biases)

    def random(nodesInMiddle:Seq[Int], range:Double = 1): NuralNetwork = 
        val numberOfNodes:Seq[Int] = 784 +: nodesInMiddle :+ 10

        val weights:Seq[Matrix] = for i <- numberOfNodes.indices.dropRight(1) yield 
            Matrix.random(numberOfNodes(i),numberOfNodes(i+1), range)

        val biases:Seq[Matrix] = for i <- numberOfNodes.indices.drop(1) yield 
         Matrix.random(1,numberOfNodes(i), range)

        new NuralNetwork(weights, biases)

    def loadFromFile(input:String):NuralNetwork = 
        val rows = input.split("\n")
        val (r, b) = 
            val temp = rows(0).split(",").map(_.toInt)
            (temp(0), temp(1))

        val weights = for i <- 1 until r yield Matrix.loadFromString(rows(i))
        val biases = for i <- 1 + r until r + b yield Matrix.loadFromString(rows(i))
        NuralNetwork(weights, biases)


