package Array.averageSum;

public class Average {

	public static void main(String[] args) {
		int a[]={3,6,5,2,9,8};
		double sum=findAverage(a);
		System.out.println(sum);
	}

	private static double findAverage(int[] a) {
		int sum=0;
		double length=a.length;
		for(int i=0;i<a.length;i++) {
			sum+=a[i];
		}
		System.out.println(sum);
		System.out.println(a.length);
		double average=sum/length;
		return average;
	}

}
