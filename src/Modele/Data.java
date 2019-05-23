package Modele;

/**
 * Data est une classe qui regroupe toutes les constantes de l'application
 */
public interface Data {
	/**
	 * Constantes sur le menu de l'application principale
	 */
	public final String[] TITRE_MENU = { "Aide", "Quitter" };

	/**
	 * Constantes sur le menu de l'application principale apr�s avoir cliqu� sur
	 * Aide
	 */
	public final String[] TITRE_MENU_LISTE = { "Simplex", "Matrice" };

	/**
	 * Constantes sur le menu de l'application principale o� on propose le choix
	 * d'aller dans l'application simplexe ou l'application matrice
	 */
	public final String[] CHOIX = { "Simplex", "Matrice" };

	/**
	 * Constantes sur le menu de l'application Matrice
	 */
	public final String[] TITRE_MATRICE = { "Retour au menu principal", "Outils", "Aide",
			"Sortir de l'application actuelle", "Quitter" };

	/**
	 * Constantes sur le menu de l'application Matrice quand on cliqu� sur le bouton
	 * Aide
	 */
	public final String[] TITRE_MATRICE_LISTE = { "Revenir en arriere", "+ (agrandir)", "- (r�tr�cir)",
			"Recommencer le calcul", "Exporter en PDF" };

	/**
	 * Constantes dans l'application Matrice sur les diff�rentes lignes possibles �
	 * exploitrer
	 */
	public final String[] LIGNES = { "L1", "L2", "L3", "L4", "L5" };

	/**
	 * Constantes correspondant aux intitul�es de la table dans l'application
	 * matrice
	 */
	public final String[] INTITULES = { "Matrice", "Identité", "Calcul", "Commentaire" };

	/**
	 * Constantes correspondant aux op�rations possibles pour l'application matrice
	 */
	public final String[] OPERATIONS = { "+", "-" };

	/**
	 * Constantes correspondant aux fl�ches possibles pour l'application matrice
	 */
	public final String[] FLECHES = { "<-", "<->" };

	/**
	 * Constante correspondant � la validation du panelMatrice
	 */
	public final String VALIDER_PANEL_MATRICE = "ValiderPanelMatrice";

	/**
	 * Constante correspondant � la validation du panelCommandes
	 */
	public final String VALIDER_PANEL_COMMANDES = "ValiderPanelCommandes";

	/**
	 * Constante correspondant � la validation du panelTaille
	 */
	public final String VALIDER_PANEL_TAILLE = "ValiderTaille";

	/**
	 * Constante correspondant au choix du mode didacticiel dans l'application
	 * matrice
	 */
	public final String VALIDER_DIDACTICIEL = "Didacticiel";

	/**
	 * Constante correspondant au choix du mode autonome dans l'application matrice
	 */
	public final String VALIDER_AUTONOME = "Autonome";

	/**
	 * Constante correspondant au choix d'effacer l'�laboration du calcul dans
	 * l'application matrice
	 */
	public final String EFFACER = "Effacer";

	/**
	 * Constante correspondant au choix de lancer un popup pour �crire une constante
	 * dans l'application matrice
	 */
	public final String CONSTANTE = "Constante";
}