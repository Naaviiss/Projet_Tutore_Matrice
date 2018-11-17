package vue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import modele.*;

public class ModelAffichageMatrices extends DefaultTableModel implements Data{
	
	//private HashMap<Integer, List<Matrice>> chMatrices;
	//private HashMap<String, String> chMatrices; // en attendant d'avoir la classe Matrice
	private HashMap<Matrice, Matrice> chMatrices;
	
	public ModelAffichageMatrices(/*private HashMap<Integer, List<Matrice>> pMatricese*/ /*HashMap<String, String> pMatrices*/ HashMap<Matrice, Matrice> pMatrices) {
		
		chMatrices = pMatrices;
		
		//on définit le modèle de la table
		this.setColumnIdentifiers(Data.INTITULES);
		this.setRowCount(20);
		
		//entrees est l'ensemble des couples clef-valeur de la hashmap chMatrices
//		Set<Entry<Integer, List<Matrice>>> entrees = chMatrices.entrySet();
//		Set<Entry<String, String>> entrees = chMatrices.entrySet();
		Set<Entry<Matrice, Matrice>> entrees = chMatrices.entrySet();
		
		//itérateur pour parcourir les entrees
//		Iterator<Entry<Integer, List<Matrice>>> it = entrees.Iterator();
//		Iterator<Entry<String,String>> it = entrees.iterator();
		Iterator<Entry<Matrice,Matrice>> it = entrees.iterator();
		
		int indiceLigne = 0;
//		Entry<Integer, List<Matrice>> entree;
//		Entry<String, String> entree;
		Entry<Matrice, Matrice> entree;

		String chaine; //inutile ?
		while (it.hasNext()) {
			entree = it.next();
			setValueAt(entree.getKey()+" A", indiceLigne, 0);
			setValueAt(entree.getKey()+" B", indiceLigne, 1);
			indiceLigne ++;
		}
		
	}
	
	public boolean isCellEditable(int indLigne, int indCol) {
		return false;
	}
	
	public Class getColumnClass(int indCol) {
		return String.class;
	}
	
}
