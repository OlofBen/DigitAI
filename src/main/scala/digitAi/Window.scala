package digitAi

import java.awt.Window
import Images.*

class Window(): 

    val window = introprog.PixelWindow(title = "Minst Data Set In Scala")
    val blockSize = 10
   
    def drawImage(imageNumber:Int):Unit = 
        require(imageNumber>0 && imageNumber< images.length, s"Not a valid image, images mus be betweet 1 and ${images.length}")
        
        val pixels = images(imageNumber).map(
            a => 
                java.awt.Color(a,a,a)
                )
        for i <- pixels.indices do window.fill(i%28*blockSize, i/28*blockSize, blockSize, blockSize, pixels(i))
    

    

    drawImage(1)

    

   
    
