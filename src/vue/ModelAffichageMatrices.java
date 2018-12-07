package vue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import modele.Data;
import modele.Matrice;

public class ModelAffichageMatrices extends DefaultTableModel implements Data{
	
	//private HashMap<Integer, List<Matrice>> chMatrices;
	private HashMap<Matrice, Matrice> chMatrices; // en attendant d'avoir la classe Matrice
	private List<String> chLigneModifiees; //pour les modifications de lignes
	private List<String> chCommentaire;//pour les eventuels commentaires
	
	public ModelAffichageMatrices(/*private HashMap<Integer, List<Matrice>> pMatricese*/ HashMap<Matrice, Matrice> pMatrices,List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = pMatrices;
		chLigneModifiees = pLigneModif;
		chCommentaire = pCommentaire;
		
		//on d�finit le mod�le de la table
		this.setColumnIdentifiers(Data.INTITULES);
		this.setRowCount(20);
		
		//entrees est l'ensemble des couples clef-valeur de la hashmap chMatrices
//		Set<Entry<Integer, List<Matrice>>> entrees = chMatrices.entrySet();
		Set<Entry<Matrice, Matrice>> entrees = chMatrices.entrySet();
		
		//it�rateur pour parcourir les entrees
//		Iterator<Entry<Integer, List<Matrice>>> it = entrees.Iterator();
		Iterator<Entry<Matrice,Matrice>> it = entrees.iterator();
		
		int indiceLigne = 0;
//		Entry<Integer, List<Matrice>> entree;
		Entry<Matrice, Matrice> entree;

		String chaine;
		while (it.hasNext()) {
			entree = it.next();
			setValueAt(entree.getKey()+" A", indiceLigne, 0);
			setValueAt(entree.getKey()+" B", indiceLigne, 1);
			indiceLigne ++;
		}
		indiceLigne = 0;
		for (int i = 0; i<chLigneModifiees.size();i++) {
			setValueAt(chLigneModifiees.get(i), indiceLigne, 2);
			setValueAt(chCommentaire.get(i), indiceLigne, 3);
			indiceLigne++;
		}
	}
	
	public boolean isCellEditable(int indLigne, int indCol) {
		return false;
	}
	
	public Class getColumnClass(int indCol) {
		return String.class;
	}
	
	public void effectueCalcul(String []tabCalcul) {
		int ligneA,ligneB;
		int ligneModifiee;
		
		Matrice matricePrincipale; //matrice principale
		Matrice matriceIdentite;//matrice identite
		
		Set<Matrice> cles = chMatrices.keySet();//set pour obtenir toutes le matrices principales
		
		//on r�cup�re la derni�re cl� qui correcpond � la derni�re matrice cr��e et donc � la matrice sur laquelle on travaille
		Iterator<Matrice> it = cles.iterator();//on cr�� un it�rateur pour parcourir le set de cl�s
		Matrice courant = null;
		while (it.hasNext()) {
			courant = it.next();
		}
		//� la fin de la boucle, courant prend la valeur de la derni�re matrice entr�e
				
		matricePrincipale = courant;
		matriceIdentite= chMatrices.get(matricePrincipale);
		
		ligneModifiee= getNumLigne(tabCalcul[0]);
		ligneB = getNumLigne(tabCalcul[6]);	//index de la deuxi�me ligne du calcul
		
		//Si l'�tudiant veut intervertir 2 lignes
		if (tabCalcul[1].equals("<->")) {
			//on effectue le potentiel calcul sur la ligne avec laquelle il souhaite �changer la ligne choisie pr�c�demment
			//on �change les lignes sur la matrice principale
			//on �change les lignes sur la matrice identit�
		}
		
		//Si l'�tudiant veut effectuer un calcul sur une ligne
		else {
			if (Arrays.asList(Data.LIGNES).contains(tabCalcul[3])) {//si c'est la deuxi�me ligne qui prend un calcul
				ligneA = getNumLigne(tabCalcul[3]); //index de la premi�re ligne du calcul
				//on effectue le calcul sur la deuxi�me ligne du calcul
			}
			else {
				ligneA = getNumLigne(tabCalcul[4]); //index de la premi�re ligne du calcul
				//on effectue le calcul sur la premi�re ligne du calcul
			}
			matricePrincipale = modi//on fait l'op�ration sur la ligne de la matrice principale
			//on fait l'op�ration sur la ligne de la matrice indentit�
		}
		
		//on ajoute la matrice principale et la matrice identit� au hashmap
		
	}
	
	//retourne l'index correspondant � la ligne
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
