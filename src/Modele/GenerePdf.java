package Modele;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerePdf {
	
	private Document document;
	
	public GenerePdf() {
		
		document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream("HelloiText.pdf"));
			document.open();
			populate(document);
		}
		catch(Exception e) {
			System.err.println(e);
		}
		finally {
			document.close();
		}
	}
	
	/**
	 * Construit un objet GenerePdf à partir d'un Historique et d'une String donnés en paramètre, la String correspondant au nom du fichier
	 * @param Historique histo
	 * @param String nomFichier
	 */
	
	public GenerePdf(Historique histo, File file) {
		
		document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			populate(document, histo);
		}
		catch(Exception e) {
			System.err.println(e);
		}
		finally {
			document.close();
		}
	}
	
	/**
	 * Ajoute un paragraphe au document pdf 
	 * @param Document doc
	 * @throws DocumentException
	 */
	
	public void populate(Document doc) throws DocumentException{
		doc.add(new Paragraph("Hello iText"));
	}
	
	/**
	 * Ajoute des paragraphes correspondant à chaque Simplexe composant l'Historique donné en paramètre dans le document pdf
	 * @param Document doc
	 * @param Historique histo
	 * @throws DocumentException
	 */
	
	public void populate(Document doc, Historique histo) throws DocumentException {
		Font f = new Font(FontFamily.COURIER);
		int i = 0;
		doc.add(new Paragraph("Listes des dictionnaires",new Font(FontFamily.TIMES_ROMAN,20,Font.BOLD | Font.UNDERLINE)));
		for(Simplexe s : histo.getListeSimplexe()) {
			doc.add(new Paragraph("\n\nDictionnaire nÂ°"+Integer.toString(i)+"\n"+s.toString2(),f));
			i++;
		}
		
		if(histo.getListeSimplexe().getLast().echangeJudicieux().contains("bénéfice")) {
			doc.add(new Paragraph("\nBénéfice maximum : "+histo.getListeSimplexe().getLast().getFonctionEco().getMonomes().get(" "),f));
		}
	}

}
