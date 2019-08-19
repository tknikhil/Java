package ReadFromConsole.usingBufferReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyBufferReader {

	public static void main(String[] args) {
		BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String name = mBufferedReader.readLine();
			System.out.println(name);
		} catch (IOException e) {e.printStackTrace();}
		
	}

}
