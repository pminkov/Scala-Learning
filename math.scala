def log2(x: Double): Double = {
  scala.math.log(x) / scala.math.log(2)
}

def roundUp(x: Double): Int = x.toInt + 1
def roundDown(x: Double): Int = x.toInt

/**
 * Number of bits in binary representation.
 */
def bits(x: Int): Int = {
  if (x == 0) 1 else roundDown(log2(x)) + 1
}

def toBinary(x: Int): String = {
  def toBinaryInternal(x: Int): String = {
    if (x == 0) ""
    else toBinaryInternal(x / 2) + ('0' + x % 2).toChar
  }

  if (x == 0) "0"
  else toBinaryInternal(x)
}

for (x <- 0 to 17) {
  println(x + " " + bits(x) + " " + toBinary(x))
}
