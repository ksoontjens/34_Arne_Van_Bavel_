import java.lang.*; //voor de verkorte schrijfwijze 

/**
* De klasse EersteProg is een java applicatie
*/

public class EersteProg{


/**
 * Dit is de main-funtctie hier start het programma args dit is een array van strings
 * Waarme parameters kunnen worden meegegeven vanaf de commandline
 */

  public static void main(String args[]) //
  {
  drukaf(100); //Het aanroepen van de functie drukaf en prameter 100 er mee naar toe sturen
  }

  private static void drukaf(int m)	// De functie drukaf
  {
    int a;		//int a word hier gedclareert is 0
    for(a=0;a<m;a++)	// 100 keer door de for loop en na elke loop a + 1 doen
	{
	  System.out.println(a);	// a elke keer afprinten
	}
  }

}