package Array.reverse;

public class ArrayReverse {

	public static void main(String[] args) {
		int a[]={3,6,5,2,9,8};
		for (int i : a) {
			System.out.print(i+" ");
		}
		System.out.println();
		int reverse[]=reverseArray(a);
		for (int i : reverse) {
			System.out.print(i+" ");
		}
	}

	private static int[] reverseArray(int[] a) {
		int lenght=a.length;
		
		for(int i=0;i<lenght/2;i++) {
			int temp=a[i];
			a[i]=a[lenght-1-i];
			a[lenght-1-i]=temp;
		}
		return a;
	}

}
