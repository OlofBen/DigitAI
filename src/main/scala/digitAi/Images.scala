package digitAi

object Images:
    val (images,answer) = {
            println("Loading images")
            val source = scala.io.Source.fromFile("train.csv", "UTF-8")
            val temp = try source.getLines.toVector finally source.close
            val temp2 = temp.tail.map(_.split(",").map(_.toInt))
            val a = temp2.map(_(0))
            val b = temp2.map(_.drop(1))
            println("Images loaded")
            (b,a)
        }

    val imagesDouble = images.map(_.map(_.toDouble))
    