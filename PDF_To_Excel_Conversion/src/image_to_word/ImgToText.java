package image_to_word;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import model.Address;

public class ImgToText {

	private static TessBaseAPI instance;
	private static PrintWriter csvFile;
	private static String output, line;
	private static Scanner readLine, scanWord;
	private static BytePointer mBytePointer;
	private static PIX image;
	private static CSVPrinter mCsvPrinter;
	private static Address mAddress;
	private static String string;
//	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		mAddress =new Address();
		instance = new TessBaseAPI();
		instance.Init("res/", "eng");
		image = lept.pixRead("res/image-0.png");
		instance.SetImage(image);
		mBytePointer = instance.GetUTF8Text();
		output = mBytePointer.getString();
		readLine = new Scanner(output);
		
		  try {
			  csvFile = new PrintWriter("res/soa.csv"); 
			  mCsvPrinter = new CSVPrinter(csvFile, CSVFormat.DEFAULT);
			  } catch (FileNotFoundException e)
		  {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		 

		while (readLine.hasNextLine()) {
			line = readLine.nextLine();
			scanWord = new Scanner(line);
			try {
				/*string = "NITHIN T KOCHRAMBIL";
				if(scanWord.hasNext(string)) {*/
				//mCsvPrinter.printRecords(scanWord.nextLine());
					mAddress.setADDRESS_NAME(scanWord.nextLine());
					mCsvPrinter.printRecord(mAddress.getADDRESS_ADDRESS());
				
			} 
			catch (IOException e) {e.printStackTrace();} 
			finally {try {mCsvPrinter.flush();} catch (IOException e) {e.printStackTrace();}}
			scanWord.close();
		}
		readLine.close();
	}
}
