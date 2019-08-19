package Array.max;

public class MaxValue {

	public static void main(String[] args) {
		int a[]={3,6,5,2,9,8};
		
		int max=findMax(a);
		System.out.println(max);
		
	}

	private static int findMax(int[] a) {
		int max=a[0];
		for(int i=0;i<a.length;i++) {
			if(a[i]>max)
				max=a[i];
		}
		return max;
	}

}
