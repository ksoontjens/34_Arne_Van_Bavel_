import java.lang.*;
import javax . tv . xlet . * ;

public class mijneerstexlet implements Xlet{

	private XletContext actueleXletContext;
	
	public void initXlet (XletContext context)
	{
		this.actueleXletContext = context;
	}
	
	// Starten van de Xlet :
	public void startXlet() throws XletStateChangeException
	{
		// Communicatie ( In¡ en Uitvoer met de gebruiker )
	}

	public void pauseXlet()
  	{
    		// Do nothing for now
  	}

	// Beëindigen van de Xlet .
	public void destroyXlet (boolean unconditional) throws XletStateChangeException
	{
		if(unconditional) 
		{
			// System.out.println geeft debug in weer voor emulatoren .
			System.out.println( "De Xlet moet beëindigd worden");
		}
		else 
		{
			System.out.println("De mogelijkheid bestaat " + "door het werpen van een exceptie " + "de Xlet in leven te houden.");
			throw new XletStateChangeException ( " Laat me leven ! " ) ;
		}
	}
}