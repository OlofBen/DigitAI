package digitAi

private class Matrix(private val matrix: Array[Array[Double]]):
    import Matrix.*

    require(matrix.forall(matrix(0).length == _.length))
    val rows = matrix.length
    val colons = matrix(0).length

    def apply(row:Int)(colon:Int):Double = matrix(row)(colon)
    def set(row:Int, colon:Int)(value:Double):Unit = matrix(row)(colon) = value

    infix def *(other:Matrix):Matrix = 
        require(colons == other.rows, "Number of rows do not match with number of Colons")
        val out:Array[Array[Double]] = 
        (for r <- 0 until rows yield
            (for k <- 0 until other.colons yield 

                (for i <- matrix(r).indices yield 
                    matrix(r)(i) * other(i)(k)
                    ).sum

            ).toArray).toArray
        Matrix(out)

    
    //Om man hade kunnat göra på plats eller i olika threads

    infix def +(other:Matrix):Matrix = 
        require(rows == other.rows && colons == other.colons, "Matrixes are not of same size")
        val out:Matrix = matrixOfSize(rows, colons)
        for r <- 0 until rows; k <- 0 until colons do 
            out.set(r,k)(apply(r)(k) + other(r)(k))
        out

    override def toString = 
        matrix.map(_.mkString("| ",",\t\t", " |")).mkString("\n","\n","")

    def makeNewRandom(range:Double = 1):Matrix = 
        random(rows, colons, range) + this

    /**makes every value in the matrix between 1 and 0 with the sigmoid function 
     * 
     * 
     * Warning: does not make a new matrix!!!!
    */
    def limited:Matrix = 
        for i <- matrix.indices do 
            for j <- matrix(i).indices do 
                matrix(i)(j) = sigmoid(matrix(i)(j))
        this
        
    
    def sigmoid(value:Double):Double = 1/(1 + math.exp(-value))


    /**Makes a string that could be loaded
     * 
     * 
     * Not the same as the toString
    */
    def saveToFile():String=
        matrix.map(_.mkString(",")).mkString("\t")
    
object Matrix: 

    def apply(a:Array[Array[Int]]):Matrix = 
        new Matrix(a.map(_.map(_.toDouble).toArray).toArray)

    def apply(a: Array[Array[Double]]):Matrix = new Matrix(a)

    def matrixOfSize(row:Int, colon:Int): Matrix = 
        Matrix(
            Array.ofDim[Double](row, colon)
        )

    def random(rows:Int, colons:Int, range:Double = 1):Matrix = 
        val random = scala.util.Random()
        val temp = Array.ofDim[Double](rows, colons)
        Matrix(temp.map(_.map(_ => random.nextDouble() * 2 * range - range)))
/**Load matrix from string
 * 
 * 
 * rows are split by \t and numbers by ,
 */
    def loadFromString(input:String):Matrix = 
        val lines = input.split("\t")
        val numbersInRows = lines.map(_.split(",").map(_.trim.toDouble))
        Matrix(numbersInRows)

