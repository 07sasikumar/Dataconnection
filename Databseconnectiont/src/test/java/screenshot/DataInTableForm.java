package screenshot;

public class DataInTableForm {
public static void main (String[]table) {
	System.out.printf("-----------------------------------%n");
	System.out.printf("|   Data In Table form   | %n");
	System.out.printf("-----------------------------------%n");
	System.out.printf("|%-10s|%-10s,|%10s|%n","sasi","kumar","ss");
	System.out.printf("-----------------------------------%n");
	//System.out.printf("|%-10s|%-10s,|%-010d|%n","sasi","kumar",7);//%-010d id not working show the error  
	System.out.printf("|%-10s|%-10s,|%010d|%n","sasi","kumar",7);
	System.out.printf("-----------------------------------%n");
	System.out.printf("|%-10s|%-10s,|%10d|%n","sasi","kumar",7);
	System.out.printf("-----------------------------------%n");
	System.out.printf("|%-10s|%-10s,|%10s|%n","sasi","kumar","7");
	System.out.printf("-----------------------------------%n");
	System.out.printf("|%-10s|%-10s,|%10s|%n","sasi","kumar",77);
	System.out.printf("-----------------------------------%n");
	/*Note :
	 * - is position 
	 * s is a string 
	 * */
}
}
