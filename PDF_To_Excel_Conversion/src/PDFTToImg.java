import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFTToImg {
	 private static final String OUTPUT_DIR = "";
	public static void main(String[] args) {
		try {
			InputStream fileReader = PdfToExcel.class.getResourceAsStream("soa_mine.pdf");
			//InputStream saveFile =  PdfToExcel.class.getResourceAsStream("/soa_mine.csv");
			//ClassLoader classLoader  = Thread.currentThread().getContextClassLoader();
			//PrintWriter csvFile = new PrintWriter("soa_mine.csv");
			
			PDDocument document = PDDocument.load(fileReader);
			PDFRenderer mPdfRenderer = new PDFRenderer(document);
			for(int page=0;page<document.getNumberOfPages();++page) {
				BufferedImage bim = mPdfRenderer.renderImageWithDPI(page,300,ImageType.RGB);
				String filename = OUTPUT_DIR+"image-"+page+".png";
				ImageIOUtil.writeImage(bim, filename, 300);
			}
			document.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
