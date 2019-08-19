/*code to convert PDF to Excel 
 * 
 * JAR-->fontbox-2
 *       pdfbox-2
 *       commons-logging-1
 *       
 *        */


import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PdfToExcel {

	private static String line =null;

	public static void main(String[] args) {
		
		try {
			
			PrintWriter csvFile = new PrintWriter("res/ExcelConvertedFile.csv");
			PDDocument document = PDDocument.load(new File("res/soa_mine.pdf"));
			if(!document.isEncrypted()) {
				PDFTextStripperByArea mPDFTextStripperByArea = new PDFTextStripperByArea();
				mPDFTextStripperByArea.setSortByPosition(true);
				PDFTextStripper mPdfTextStripper = new PDFTextStripper();
				String readDocument = mPdfTextStripper.getText(document);
				
				Scanner scanLine = new Scanner(readDocument);
				
				while (scanLine.hasNextLine()) {
					line  = scanLine.nextLine();
					
					Scanner scanWord = new Scanner(line);
					//System.out.println(scanWord.nextLine().matches("NITHIN T KOCHARAMBIL"));
					if(scanWord.nextLine().matches("NITHIN T KOCHARAMBIL"))
							System.out.println(scanWord);
			
					//csvFile.println(scanWord.nextLine());//Here we have to put loop for each "scanWord" 
					
					scanWord.close();
					
				}
				scanLine.close();
			}
			
			document.close();
			csvFile.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
