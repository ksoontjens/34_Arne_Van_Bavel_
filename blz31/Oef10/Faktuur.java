public class Faktuur implements Betaalbaar
{
	public int factuurNr;
	public float factuurBedrag;

	public void betaal()
	{
	System.out.println("Betaal het facktuur " + factuurNr+" voor een bedrag van " + factuurBedrag);
	}

}