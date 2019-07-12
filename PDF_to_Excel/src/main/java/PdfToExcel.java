import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class PdfToExcel {
	private static String mIMAGE_FILE_NAME,mREAD_FROM_IMAGE,mREAD_LINE;
	private static Scanner readLine,scanWord;
	
	private static String mINPUT_FILE_PATH = "pdf/soa_mine.pdf";
	private static String mOUTPUT_FILE_PATH="src/main/resources/img/";
	
	private static InputStream mInputStream;
	private static PDDocument mPdDocument;
	private static PDFRenderer mPdfRenderer;
	private static BufferedImage mBufferedImage;
	
	private static TessBaseAPI mTessBaseAPI;
	private static PrintWriter mCSVFile;
	private static BytePointer mBytePointer;
	private static PIX mImage;
	private static CSVPrinter mCsvPrinter;
	
	public static void main(String[] args) {
		pdfToImage();
	}

	private static void pdfToImage() {
		mInputStream = PdfToExcel.class.getResourceAsStream(mINPUT_FILE_PATH);
		try {
			mPdDocument = PDDocument.load(mInputStream);
			mPdfRenderer = new PDFRenderer(mPdDocument);
			for(int page=0;page<mPdDocument.getNumberOfPages();++page) {
				mBufferedImage = mPdfRenderer.renderImageWithDPI(page,300,ImageType.RGB);
				mIMAGE_FILE_NAME = mOUTPUT_FILE_PATH +"image-"+page+".png";
				ImageIOUtil.writeImage(mBufferedImage, mIMAGE_FILE_NAME, 300);
			}
			mPdDocument.close();
		} catch (IOException e){e.printStackTrace();}
		
		imageToExcel();
	}

	private static void imageToExcel() {
		mTessBaseAPI = new TessBaseAPI();
		mTessBaseAPI.Init("src/main/resources/file/", "eng");
		mImage = lept.pixRead("src/main/resources/img/image-0.png"); //Need to  iterate every image not single image
		mTessBaseAPI.SetImage(mImage);
		mBytePointer = mTessBaseAPI.GetUTF8Text();
		mREAD_FROM_IMAGE = mBytePointer.getString();

		readLine = new Scanner(mREAD_FROM_IMAGE);

		try {
			mCSVFile = new PrintWriter("src/main/resources/file/soa.csv"); //Need Unique Name to every single file
			mCsvPrinter = new CSVPrinter(mCSVFile, CSVFormat.DEFAULT);
		} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		
		while (readLine.hasNextLine()) {
			mREAD_LINE = readLine.nextLine();
			scanWord = new Scanner(mREAD_LINE);
			// System.out.println(scanWord.nextLine());
			try {
				mCsvPrinter.printRecord(scanWord.nextLine());
			} catch (IOException e) {e.printStackTrace();}finally {try {mCsvPrinter.flush();} catch (IOException e) {e.printStackTrace();}}
			scanWord.close();
		}
		readLine.close();
	}
}
