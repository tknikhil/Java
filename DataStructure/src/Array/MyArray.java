package Array;

import java.util.Scanner;

public class MyArray {

	public static void main(String[] args) {
		int a[] =new int[5];
		Scanner sc= new Scanner(System.in);
		for(int i=0;i<a.length;i++) {
			a[i]=sc.nextInt();
		}
		
		for (int i : a) {
			System.out.print(i+" ");
		} 
	}

}
