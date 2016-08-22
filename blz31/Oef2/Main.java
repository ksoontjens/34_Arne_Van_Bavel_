import java.lang.*;

public class Main
{
public static void main(String args[])
{	
	Werknemer jef = new Werknemer("jef","bonbon",1,2000);
	Werknemer fred = new Werknemer("fred","meeuwsen",2,1854);
	Werknemer mark = new Werknemer("mark","leopold",3,2250);
	Werknemer karel = new Werknemer("karel","van nieuwkereken",4,1200);
	
	jef.salarisVerhogen(20);
	fred.salarisVerhogen(50);
	
	System.out.println(jef.getSalaris());
	System.out.println(fred.getSalaris());
	System.out.println(mark.getSalaris());
	System.out.println(karel.getSalaris());
}
}