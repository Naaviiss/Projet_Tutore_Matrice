package modele;

public interface Data {
	public final String [] Titre_Menu={"Aide","Retour au menu principal","Quitter"};
	public final String [] Titre_Menu_Liste={"Simplex","Matrice"};
	public final String [] CHOIX={"Simplex","Matrice"};

	static String[] LIGNES = {"L1","L2","L3","L4","L5"};
	static String[] INTITULES = {"Matrice","Identité","Calcul"};
	static String[] OPERATIONS = {"+","-","*","/"};
	
	public final String VALIDER_PANEL_MATRICE = "ValiderPanelMatrice";
	public final String VALIDER_PANEL_COMMANDES = "ValiderPanelCommandes";
	public final String VALIDER_PANEL_TAILLE = "ValiderTaille";
}
