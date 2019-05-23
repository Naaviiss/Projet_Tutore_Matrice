package vue;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Data;
import Modele.Matrice;

/**
 * ModelAffichageMatrices est une classe qui permet d'attribuer un modèle
 * spécifique à la table. Elle permet notamment d'interroger la table
 */
public class ModelAffichageMatrices extends DefaultTableModel implements Data {

	/**
	 * Clé de hachage SHA qui identifie de manière unique ModelAffichageMatrices
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Liste des matrices
	 */
	private List<Matrice> chMatrices;

	/**
	 * Liste des matrices identitées
	 */
	private List<Matrice> chMatricesID;

	/**
	 * Liste des modifications de lignes
	 */
	private List<String> chLigneModifiees;

	/**
	 * Liste des commentaires
	 */
	private List<String> chCommentaire;

	/**
	 * Constructeur par défaut de la classe ModelAffichageMatrices
	 * 
	 * @param pMatrices
	 *            une liste de matrices
	 * @param pMatricesID
	 *            une liste de matrices identitées
	 * @param pLigneModif
	 *            une liste de modifications de lignes
	 * @param pCommentaire
	 *            une liste de commantires
	 */
	public ModelAffichageMatrices(List<Matrice> pMatrices, List<Matrice> pMatricesID, List<String> pLigneModif,
			List<String> pCommentaire) {
		chMatrices = pMatrices;
		chMatricesID = pMatricesID;
		chLigneModifiees = pLigneModif;
		chCommentaire = pCommentaire;

		// on définit le modéle de la table
		this.setColumnIdentifiers(Data.INTITULES);
		this.setRowCount(20);

		int indiceLigne = 0;
		System.out.println(chMatrices.size());
		for (int i = 0; i < chMatrices.size(); i++) {
			setValueAt(chMatrices.get(i), indiceLigne, 0);
			setValueAt(chMatricesID.get(i), indiceLigne, 1);
			setValueAt(chLigneModifiees.get(i), indiceLigne, 2);
			setValueAt(chCommentaire.get(i), indiceLigne, 3);
			indiceLigne++;
		}
	}

	/**
	 * Renvoie vrai si la cellule est éditable
	 * 
	 * @param indLigne
	 *            la ligne de la cellule
	 * @param indCol
	 *            la colonne de la cellule
	 * @return vrai si la cellule est éditable, sinon faux.
	 */
	@Override
	public boolean isCellEditable(int indLigne, int indCol) {
		return false;
	}

	/**
	 * Renvoie la superclass la plus spécifique pour toutes les valeurs des cellules
	 * de la colonne.
	 * 
	 * @param indCol
	 *            l'index de la colonne
	 * @return la superclass la plus spécifique pour toutes les valeurs des cellules
	 *         de la colonne.
	 */
	@Override
	public Class<String> getColumnClass(int indCol) {
		return String.class;
	}
}