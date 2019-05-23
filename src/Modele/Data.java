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
	 * Constantes sur le menu de l'application principale après avoir cliqué sur
	 * Aide
	 */
	public final String[] TITRE_MENU_LISTE = { "Simplex", "Matrice" };

	/**
	 * Constantes sur le menu de l'application principale où on propose le choix
	 * d'aller dans l'application simplexe ou l'application matrice
	 */
	public final String[] CHOIX = { "Simplex", "Matrice" };

	/**
	 * Constantes sur le menu de l'application Matrice
	 */
	public final String[] TITRE_MATRICE = { "Retour au menu principal", "Outils", "Aide",
			"Sortir de l'application actuelle", "Quitter" };

	/**
	 * Constantes sur le menu de l'application Matrice quand on cliqué sur le bouton
	 * Aide
	 */
	public final String[] TITRE_MATRICE_LISTE = { "Revenir en arriere", "+ (agrandir)", "- (rétrécir)",
			"Recommencer le calcul", "Exporter en PDF" };

	/**
	 * Constantes dans l'application Matrice sur les différentes lignes possibles à
	 * exploitrer
	 */
	public final String[] LIGNES = { "L1", "L2", "L3", "L4", "L5" };

	/**
	 * Constantes correspondant aux intitulées de la table dans l'application
	 * matrice
	 */
	public final String[] INTITULES = { "Matrice", "IdentitÃ©", "Calcul", "Commentaire" };

	/**
	 * Constantes correspondant aux opérations possibles pour l'application matrice
	 */
	public final String[] OPERATIONS = { "+", "-" };

	/**
	 * Constantes correspondant aux flèches possibles pour l'application matrice
	 */
	public final String[] FLECHES = { "<-", "<->" };

	/**
	 * Constante correspondant à la validation du panelMatrice
	 */
	public final String VALIDER_PANEL_MATRICE = "ValiderPanelMatrice";

	/**
	 * Constante correspondant à la validation du panelCommandes
	 */
	public final String VALIDER_PANEL_COMMANDES = "ValiderPanelCommandes";

	/**
	 * Constante correspondant à la validation du panelTaille
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
	 * Constante correspondant au choix d'effacer l'élaboration du calcul dans
	 * l'application matrice
	 */
	public final String EFFACER = "Effacer";

	/**
	 * Constante correspondant au choix de lancer un popup pour écrire une constante
	 * dans l'application matrice
	 */
	public final String CONSTANTE = "Constante";
}