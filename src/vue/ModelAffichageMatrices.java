package vue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import modele.Data;
import modele.Fraction;
import modele.Matrice;

public class ModelAffichageMatrices extends DefaultTableModel implements Data{
	
	private List<Matrice> chMatrices; // liste des matrices
	private List<Matrice> chMatricesID; // list des matrices identités
	private List<String> chLigneModifiees; //pour les modifications de lignes
	private List<String> chCommentaire;//pour les eventuels commentaires
	
	public ModelAffichageMatrices(List<Matrice> pMatrices,List<Matrice> pMatricesID,List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = pMatrices;
		chMatricesID = pMatricesID;
		chLigneModifiees = pLigneModif;
		chCommentaire = pCommentaire;
		
		//on définit le modèle de la table
		this.setColumnIdentifiers(Data.INTITULES);
		this.setRowCount(200);
		
		int indiceLigne = 0;
		System.out.println(chMatrices.size());
		for (int i = 0; i<chMatrices.size();i++) {
			setValueAt(chMatrices.get(i), indiceLigne, 0);
			setValueAt(chMatricesID.get(i), indiceLigne, 1);
//			setValueAt(chLigneModifiees.get(i), indiceLigne, 2);
//			setValueAt(chCommentaire.get(i), indiceLigne, 3);
			indiceLigne++;
		}
	}
	
	public boolean isCellEditable(int indLigne, int indCol) {
		return false;
	}
	
	public Class<String> getColumnClass(int indCol) {
		return String.class;
	}
	
	public void effectueCalcul(String []tabCalcul) {
		int ligneB;
		int ligneModifiee;
		
		Matrice matricePrincipale; //matrice principale
		Matrice matriceIdentite;//matrice identite
		
		//on recupere la derniere matrice de chaque liste
		matricePrincipale = chMatrices.get(chMatrices.size());
		matriceIdentite = chMatricesID.get(chMatricesID.size());
		
		ligneModifiee= getNumLigne(tabCalcul[0]);
		ligneB = getNumLigne(tabCalcul[6]);	//index de la deuxième ligne du calcul
		
		//Si l'étudiant veut intervertir 2 lignes
		if (tabCalcul[1].equals("<->")) {
			matricePrincipale.modifyLine(ligneModifiee, new Fraction(tabCalcul[2]));//on effectue le potentiel calcul sur la ligne avec laquelle il souhaite échanger la ligne choisie précédemment
			matriceIdentite.modifyLine(ligneModifiee, new Fraction(tabCalcul[2]));
			//on échange les lignes sur la matrice principale
			//on échange les lignes sur la matrice identité
		}
		
		//Si l'étudiant veut effectuer un calcul sur une ligne
		else {
			if (Arrays.asList(Data.LIGNES).contains(tabCalcul[3])) {//si c'est la deuxième ligne qui prend un calcul
				matricePrincipale.modifyLine2(ligneModifiee, tabCalcul[4], ligneB, new Fraction(tabCalcul[5]));//on fait l'opération sur la ligne de la matrice principale
				matriceIdentite.modifyLine2(ligneModifiee, tabCalcul[4], ligneB, new Fraction(tabCalcul[5]));//on fait l'opération sur la ligne de la matrice identité
			}
			else {
				matricePrincipale.modifyLine(ligneModifiee, new Fraction(tabCalcul[2]));//on fait l'opération sur la ligne de la matrice principale
				matriceIdentite.modifyLine(ligneModifiee, new Fraction(tabCalcul[2]));//on fait l'opération sur la ligne de la matrice identité
			}
		}
		
		chMatrices.add(matricePrincipale);//on ajoute la matrice à la liste
		chMatricesID.add(matriceIdentite);//on ajoute la matrce identit à la liste des matrices identités
		this.fireTableDataChanged();//on met la table à jour
	}
	
	//retourne l'index correspondant à la ligne
	public int getNumLigne(String ligne) {
		if (ligne.equals("L1"))
				return 0;
		else if (ligne.equals("L2"))
				return 1;
		else if (ligne.equals("L3"))
				return 2;
		else if (ligne.equals("L4"))
				return 3;
		else
				return 4;
	}	
}
