
public class MyString {
	public static void main(String[] args) {
		String s= "Hello World";
		System.out.println(s);
		String p = reverseString(s);
		System.out.println(p);
	}

	private static String reverseString(String s) {
		int length=s.length();
		String reverse="";
		for(int i=length-1;i>=0;i--) {
			reverse +=s.charAt(i);
		}
		return reverse;
	}

}
