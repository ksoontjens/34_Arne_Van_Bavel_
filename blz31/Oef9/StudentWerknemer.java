public class StudentWerknemer extends PartTimeWerknemer
{

	private float RSZpercentage = 5;
	
	public StudentWerknemer(String voornaam,String achternaam,int nr,float sal , int urengw)
	{
		super(voornaam,achternaam,nr,sal,urengw);

	}

	public void setRSZ(float RSZ)
	{
	RSZpercentage = RSZ;
	}

	public float getRSZ()
	{
	return RSZpercentage;
	}
}