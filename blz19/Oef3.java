import java.lang.*;

public class Oef3
{
public static void main(String args[])
{	
	double b = 1;
	double uitkomst = 0;
	for(int i = 0; i < 10000; i++)
	{
		if((b + i)%2 == 0)
		{
		uitkomst += -1 / b;
		}
		else
		{
		uitkomst += 1/ b;
		}

		b=b+2;
	}

	System.out.println(4*uitkomst);
}
}