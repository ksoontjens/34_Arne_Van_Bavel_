public class Werknemer implements Betaalbaar
{
	public String voornaam;
	public String achternaam;
	public int werknemerNummer;
	protected float salaris;
	private float RSZpercentage = 33;

	public Werknemer(String voornaam, String achternaam, int wNummer, float salaris)
	{
	this.voornaam = voornaam;
	this.achternaam = achternaam;
	werknemerNummer = wNummer;
	this.salaris = salaris;
	} 

	public void salarisVerhogen(int percentage)
	{
	float verhogingsfactor = (float)percentage/100;
	salaris += salaris * verhogingsfactor;
	}

	public float getSalaris()
	{
	return salaris;
	}
	
	public void setRSZ(float RSZ)
	{
	RSZpercentage = RSZ;
	}

	public float getRSZ()
	{
	return RSZpercentage;
	}

	public void betaal()
	{
	System.out.println("Betaal het Salaris" + salaris + " aan de werknemer" + voornaam);
	}

}