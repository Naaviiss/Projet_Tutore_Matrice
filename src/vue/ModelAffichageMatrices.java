package vue;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Data;
import Modele.Matrice;

public class ModelAffichageMatrices extends DefaultTableModel implements Data{
	private static final long serialVersionUID = 1L;
	private List<Matrice> chMatrices; // liste des matrices
	private List<Matrice> chMatricesID; // list des matrices identit�s
	private List<String> chLigneModifiees; //pour les modifications de lignes
	private List<String> chCommentaire;//pour les eventuels commentaires
	
	public ModelAffichageMatrices(List<Matrice> pMatrices,List<Matrice> pMatricesID,List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = pMatrices;
		chMatricesID = pMatricesID;
		chLigneModifiees = pLigneModif;
		chCommentaire = pCommentaire;
		
		//on définit le modèle de la table
		this.setColumnIdentifiers(Data.INTITULES);
		this.setRowCount(20);
		
		int indiceLigne = 0;
		System.out.println(chMatrices.size());
		for (int i = 0; i<chMatrices.size();i++) {
			setValueAt(chMatrices.get(i), indiceLigne, 0);
			setValueAt(chMatricesID.get(i), indiceLigne, 1);
			setValueAt(chLigneModifiees.get(i), indiceLigne, 2);
			setValueAt(chCommentaire.get(i), indiceLigne, 3);
			indiceLigne++;
		}
	}
	
	@Override
	public boolean isCellEditable(int indLigne, int indCol) {
		return false;
	}
	
	@Override
	public Class<String> getColumnClass(int indCol) {
		return String.class;
	}
}
