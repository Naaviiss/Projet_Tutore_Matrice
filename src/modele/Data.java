package modele;

public interface Data {
	public final String [] TITRE_MENU={"Aide","Retour au menu principal","Quitter"};
	public final String [] TITRE_MENU_LISTE={"Simplex","Matrice"};
	public final String [] CHOIX={"Simplex","Matrice"};

	public static String[] LIGNES = {"L1","L2","L3","L4","L5"};
	public static String[] INTITULES = {"Matrice","Identité","Calcul","Commentaire"};
	public static String[] OPERATIONS = {"+","-","*","/"};
	public static String []FLECHES = {"<-","<->"};
	
	public final String VALIDER_PANEL_MATRICE = "ValiderPanelMatrice";
	public final String VALIDER_PANEL_COMMANDES = "ValiderPanelCommandes";
	public final String VALIDER_PANEL_TAILLE = "ValiderTaille";
	
}
