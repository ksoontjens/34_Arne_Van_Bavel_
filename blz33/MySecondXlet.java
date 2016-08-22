public class MySecondXlet implements javax.tv.xlet.Xlet
{

  	private javax.tv.xlet.XletContext context;
	private boolean hasBeenStarted;

  public MySecondXlet()
  {

  }

  public void initXlet(javax.tv.xlet.XletContext context)
    throws javax.tv.xlet.XletStateChangeException
  {
    this.context = context;
    hasBeenStarted = false;
    System.out.println("The initXlet() method has been" +
      " called.  Our Xlet context is " + context);
  }
  
	public void startXlet()
    throws javax.tv.xlet.XletStateChangeException
  {
    if (hasBeenStarted) {
      System.out.println("The startXlet() method has" +
        " been called to resume the Xlet after it's" +
        " been paused.  Hello again, world!");
    }
    else {
      System.out.println("The startXlet() method has" +
        " been called to start the Xlet for the" +
        " first time.  Hello, world!");
      hasBeenStarted = true;
    }
  }

  public void pauseXlet()
  {
    System.out.println("The pauseXlet() method has " +
      "been called. Bedtime...");
  }

  public void destroyXlet(boolean unconditional)
    throws javax.tv.xlet.XletStateChangeException
  {
    if (unconditional) {
      System.out.println("The destroyXlet() method has" +
        " been called telling the  Xlet to stop" +
        " unconditionally.  Goodbye, cruel world!");
    }
    else {
      System.out.println("The destroyXlet() method has" +
        " been called requesting that the application" +
        " stops, but giving it the choice.  So, I'll" +
        " decide not to  stop.");

      throw new XletStateChangeException(
        "Please don't kill me!");
    }
  }
}