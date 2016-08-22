import java.lang.*;

public class Oef2{
public static void main(String args[])
{
	int a = 0;
	String dag[]={"Zondag","Maandag","Dindsdag","Woendsdag","Donderdag","Vrijdag","Zaterdag"};
	for(int i = 1; i<29; i++)
	{
		if(a == 7){a = 0;}
			System.out.println(dag[a] + " " + i + " Februari");
		a++;
	}
}

}