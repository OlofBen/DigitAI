package digitAi

object IO:
    def svaveString(nn:NuralNetwork) = 
        val file = "SOME FILE"
        java.io.File(file).delete()
        introprog.IO.saveString(nn.saveToFile(),file)
        

    def loadSrting(nn:NuralNetwork) = 
        val file = "SOME FILE"
        introprog.IO.loadString(file)
