import java.lang.Thread

class Runner {
  val TriesLimit = 5 

  def runWithRetries[T](op: () => Unit, tryIndex: Int = 0): Unit = {
    try { 
      op()
    } catch {
      case e => {
        println("Exception :(. " + e)
        val sleepSeconds = 1 << tryIndex
        println("Sleeping for " + sleepSeconds + " before retrying.")
        Thread.sleep(sleepSeconds * 1000)

        val triedTimes = tryIndex + 1
        if (triedTimes < TriesLimit) {
          runWithRetries(op, tryIndex + 1)
        } else {
          println("Tried " + TriesLimit + " times. Giving up.")
        }
      }
    }
  }

  def doWork() {
    runWithRetries(() => { println ("hi, how are you?") })
    runWithRetries(() => { println ("bye") })

    var runCount = 0
    // Throws exception only a few times.
    runWithRetries(() => {
      runCount = runCount + 1
      println("ONE. This is run " + runCount)
      if (runCount < 3) {
        throw new Exception 
      }
    })

    runCount = 0
    // Always throws exception.
    runWithRetries(() => {
      runCount = runCount + 1
      println("TWO. This is run " + runCount)
      throw new Exception 
    })
  }
}

val r = new Runner
r.doWork()
