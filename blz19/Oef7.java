import java.lang.*;

public class Oef7
{
public static void main(String args[])
{	
	int grootste = 0;
	int a[] = {12,34,56,78,123,234,99,88};
	int b[] = new int[a.length];

	for(int j = 0; j < a.length; j++)
	{
	int positie = 0;

		for(int i = 0; i < a.length; i++)
		{
			if(a[i] > grootste)
			{
				grootste = a[i];
				positie = i;
			}
		}
	b[j] = grootste;
	grootste = 0;
	a[positie] = 0;
	
	}
	
	for(int i = 0; i < b.length; i++)
	{
	System.out.println(b[i]);
	}
}
}