package vue;

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
	
	private HashMap<Matrice, Matrice> chMatrices; // en attendant d'avoir la classe Matrice
	private List<String> chLigneModifiees; //pour les modifications de lignes
	private List<String> chCommentaire;//pour les eventuels commentaires
	
	public ModelAffichageMatrices(HashMap<Matrice, Matrice> pMatrices,List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = pMatrices;
		chLigneModifiees = pLigneModif;
		chCommentaire = pCommentaire;
		
		//on définit le modèle de la table
		this.setColumnIdentifiers(Data.INTITULES);
		this.setRowCount(200);
		
		//entrees est l'ensemble des couples clef-valeur de la hashmap chMatrices
		Set<Entry<Matrice, Matrice>> entrees = chMatrices.entrySet();
		
		//itérateur pour parcourir les entrees
		Iterator<Entry<Matrice,Matrice>> it = entrees.iterator();
		
		int indiceLigne = 0;
		Entry<Matrice, Matrice> entree;

		while (it.hasNext()) {
			entree = it.next();
			setValueAt(entree.getKey(), indiceLigne, 0);
			setValueAt(entree.getKey(), indiceLigne, 1);
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
	
	public Class<String> getColumnClass(int indCol) {
		return String.class;
	}
	
	public void effectueCalcul(String []tabCalcul) {
		int ligneB;
		int ligneModifiee;
		
		Matrice matricePrincipale; //matrice principale
		Matrice matriceIdentite;//matrice identite
		
		Set<Matrice> cles = chMatrices.keySet();//set pour obtenir toutes le matrices principales
		
		//on récupère la dernière clé qui correcpond à la dernière matrice créée et donc à la matrice sur laquelle on travaille
		Iterator<Matrice> it = cles.iterator();//on créé un itérateur pour parcourir le set de clés
		Matrice courant = null;
		while (it.hasNext()) {
			courant = it.next();
		}
		//à la fin de la boucle, courant prend la valeur de la dernière matrice entrée
				
		matricePrincipale = courant;
		matriceIdentite= chMatrices.get(matricePrincipale);
		
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
		
		chMatrices.put(matricePrincipale, matriceIdentite);//on ajoute la matrice principale et la matrice identité au hashmap
		
		this.fireTableStructureChanged();//on met la table à jour
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
