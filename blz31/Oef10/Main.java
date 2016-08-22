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
	
	PartTimeWerknemer parttime1 = new PartTimeWerknemer("karelt","van nieuwkereken",5,500,50);
	PartTimeWerknemer parttime2 = new PartTimeWerknemer("karelt","van nieuwkereken",6,400,40);
	
	parttime1.salarisVerhogen(10);
	parttime2.salarisVerhogen(4);
	System.out.println(parttime1.getSalaris());
	System.out.println(parttime2.getSalaris());

	jef.setRSZ(10);
	System.out.println(jef.getRSZ());
	System.out.println(parttime1.getRSZ());

	StudentWerknemer Student1 = new StudentWerknemer("arne","van bavel",7,800,60);
	System.out.println(Student1.getRSZ());
	
	jef.betaal();
	parttime1.betaal();
	Student1.betaal();

	
	Faktuur Faktuur1 = new Faktuur();
	Faktuur1.betaal();
	
		
}
}