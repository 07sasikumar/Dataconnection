package arrayworkout;

import java.util.Arrays;
import java.util.Scanner;

public class Arrayor {
	public static void main(String[] arg) {
Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of elements you want to store: ");
        int a = sc.nextInt();
        
        // Creating an array in the memory with length 'a'
        int[] array = new int[a];
        
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < a; i++) {
            // Reading array elements from the user
            array[i] = sc.nextInt();
        }
        
        // Sorting the array in ascending order
        Arrays.sort(array);
        
        System.out.println("Array elements in ascending order: ");
        for (int i = 0; i < a; i++) {
            System.out.println(array[i]);
        }
        
        // Closing the scanner
        sc.close();
}

}
