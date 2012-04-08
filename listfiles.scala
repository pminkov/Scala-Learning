import java.io.File

val f = new File(".")
val all = f.listFiles

all.foreach(file => {
  println(file)
})
