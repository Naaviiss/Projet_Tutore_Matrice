package modele;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vue.PanelAffichageMatrices;

public class TablePDF {
		
	public void createPDF(String pDestination, PanelAffichageMatrices pPanel) throws IOException, DocumentException,FileNotFoundException, DocumentException {
		/*Prend en paramètre la destination du PDF et le panel dans lequel se trouvent les données de la table*/
		
		//on récupère les données de la table
		List<Matrice> chMatrices = pPanel.getChMatrices();
		List<Matrice> chMatricesID = pPanel.getChMatricesID();
		List<String> chLigneModif= pPanel.getChLigneModif();
		List<String> chCommentaire = pPanel.getChCommentaire();
		
		Document myDoc = new Document(PageSize.A4,30,30,30,30); //notre document au format A4
		
		PdfWriter.getInstance(myDoc, new FileOutputStream(pDestination)); //on écrit sur le document
		
		myDoc.open(); //on l'ouvre pour y écrire
		
		float largeurColonnes[] = {2,2,2,2}; //largeur des colonnes
		
		PdfPTable tablePdf = new PdfPTable(largeurColonnes); //table qui sera ajoutée au pdf
		
		tablePdf.setWidthPercentage(100); //pour que le document prenne toute la page
		
		tablePdf.getDefaultCell().setUseAscender(true);
		tablePdf.getDefaultCell().setUseDescender(true);
		
		//on fait les entetes
		tablePdf.getDefaultCell().setBackgroundColor(BaseColor.ORANGE);
		for(int i=0;i<4;i++) {
			tablePdf.addCell("Matrices");
			tablePdf.addCell("Matrices Identité");
			tablePdf.addCell("Opérations");
			tablePdf.addCell("Commentaires");
		}
		
		//on remplit avec les données
		tablePdf.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
		tablePdf.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int i = 0; i<chMatrices.size();i++) {
			tablePdf.addCell(chMatrices.get(i).toString());
			tablePdf.addCell(chMatricesID.get(i).toString());
			tablePdf.addCell(chLigneModif.get(i));
			tablePdf.addCell(chCommentaire.get(i));
		}
		
		myDoc.add(tablePdf); //on ajoute la table au document
		
		myDoc.close(); //on pense à le fermer
		
	}
}
