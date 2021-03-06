public final class IntervalTimer {
 private static final boolean GC = false;
 
  private long startTime;

    private long endTime;

  /**
   * Flag to indicate whether timer is active.
   */

  private boolean timing;


 /**
   * Starts the timer.
   */

  public void start() {
    if (!timing) {

      if (GC)          // invoke garbage collector explicitly
	System.gc();   // to minimise possible effects on timing

      startTime = System.currentTimeMillis();
      endTime = 0L;
      timing = true;

    }
  }


  /**
   * Calculates elapsed time.
   * @return Current elapsed time in seconds, as a real number
   */

  public double getElapsedTime() {
    if (timing) {
      long now = System.currentTimeMillis();
      return (now - startTime) / 1000.0;
    }
    else 
      return (endTime - startTime) / 1000.0;
  }


  /**
   * Stops the timer (if it is running).
   * @return Total elapsed time in seconds, as a real number
   */

  public double stop() {
    if (timing) {
      endTime = System.currentTimeMillis();
      timing = false;
    }
    return (endTime - startTime) / 1000.0;
  }


  /**
   * Resets the timer.
   */

  public void reset() {
    startTime = endTime = 0L;
    timing = false;
  }


  /**
   * Indicates whether timer is currently active.
   * @return True if the timer is active, false otherwise
   */

  public boolean isTiming() {
    return timing;
  }


  /**
   * Indicates whether timer is currently inactive.
   * @return True if the timer is inactive, false otherwise
   */

  public boolean isStopped() {
    return (!timing);
  }


  /**
   * Creates a String representation of timer status.
   * @return Timer status, as a String
   */

  public String toString() {

    if (startTime == 0L && endTime == 0L)
      return new String(getClass().getName() + ": unused");

    if (timing)
      return new String(getClass().getName() + ": started " + startTime);
    else
      return new String(getClass().getName() + ": started " + startTime +
       ", stopped " + endTime);

  }


}
