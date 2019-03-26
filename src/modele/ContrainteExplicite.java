package modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContrainteExplicite implements Serializable{
	String nom;
	Map monomes;
	Fraction inferieurA;
	int nombreInconnues;
	
	/**
	 * Construit un objet ContrainteExplicite et remplit les champs Fraction inferieurA et String nom avec les variables
	 * données en paramètre ( les autres champs sont une HashMap vide et un nombre d'inconnues initialisé à 0)
	 * @param Fraction limite
	 * @param String nom
	 */
	public ContrainteExplicite(Fraction limite, String nom){
		this.monomes = new HashMap();
		inferieurA=limite;
		nombreInconnues=0;
		this.nom=nom;
	}
	
	/**
	 * Construit un objet ContrainteExplicite à partir d'un autre objet ContrainteExplicite donné en paramètre et copie 
	 * tous les champs de la ContrainteExplicite en paramètre dans le nouvel objet qui sera construit
	 * @param ContrainteExplicite ce
	 */
	public ContrainteExplicite(ContrainteExplicite ce) {
		// TODO Auto-generated constructor stub
		this.nom = ce.nom;
		this.inferieurA = new Fraction(ce.inferieurA);
		this.nombreInconnues = ce.nombreInconnues;
		monomes = new HashMap();
		for (Iterator i = ce.monomes.keySet().iterator(); i.hasNext(); ) {
			String clé = (String) i.next();
			this.ajouterMonome(new Monome((Monome)ce.monomes.get(clé)));;
		}
	}
	/**
	 * 
	 * @param Monome m
	 * Permet l'ajout d'un monome dans le champs monome (Map)
	 */
	public void ajouterMonome(Monome m) {
		monomes.put(m.getInconnue(), m);
	}
	
	/**
	 * Permet de former le dictionnaire n°1 : on rentre les limites dans chaque contrainte.
	 */
	public void passageDico1() {
		for (Iterator i = monomes.keySet().iterator(); i.hasNext(); ) {
			String clé = (String) i.next();
			((Monome)monomes.get(clé)).setCoefficient((((Monome)monomes.get(clé)).getCoefficient().FMultiplication(new Fraction(-1,1))));
		}
		Monome m = new Monome(inferieurA, " ");
		this.ajouterMonome(m);
	}
	
	/* toString pour le dictionnaire 0 (sans les "=", seulement les contraintes avec les "<=")*/
	
	/**
	 * Affiche le premier dictionnaire qui correspond au dictionnaire ou chaque contrainte doit être inférieure à une limite
	 * @return String chaineFinale 
	 * 
	 */
	
	public String toStringDico1() {
		String chaineFinale = new String();
		Iterator i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String clé = (String) i.next();
			chaineFinale+=((Monome) monomes.get(clé)).toString();
		}
		
		while(i.hasNext()) {
			String clé = (String) i.next();
			if(((Monome)monomes.get(clé)).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" + ";
			}
			chaineFinale += ((Monome) monomes.get(clé)).toString();
		}
		chaineFinale+= " <= "+ this.inferieurA;
		return chaineFinale;
	}
	
	/* toString pour les autres dictionnaires (avec les "=")*/
	
	/**
	 * @return String chaineFinale: renvoie une chaine de caractères correspondant à un dictionnaire ( autre que le dictionnaire n°1)
	 * Permet l'affichage des autres dictionnaires(autre que le dictionnaire n°1)
	 */
	public String toString() {
		String chaineFinale = new String();
		chaineFinale +=  this.nom + " = ";
		Iterator i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String clé = (String) i.next();
			chaineFinale+=((Monome) monomes.get(clé)).toString();
		}
		
		while(i.hasNext()) {
			String clé = (String) i.next();
			if(((Monome)monomes.get(clé)).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" +";
			}
			chaineFinale += " " + ((Monome) monomes.get(clé)).toString();
		}
		return chaineFinale;

	}
	
	/*Parcourt la HashMap <nom de l'inconnue, Monome> de this. Additionne chaque Monome à celui ayant la même clé dans la HashMap de la 
	 * Contrainte explicite en paramètre
	 */
	
	/**
	 * 
	 * @param ContrainteExplicite ce: renvoie une contrainte explicite
	 * Parcourt la HashMap<nom de l'inconnue,Monome> de this et additionne chaque Monome à celui ayant la même clé dans la HashMap
	 * de ContrainteExplicite donnée en paramètre
	 */
	public void additionnerLigne(ContrainteExplicite ce) {
		Iterator i = monomes.keySet().iterator(); 
		while(i.hasNext()) {
			String clé = (String) i.next();
			((Monome) monomes.get(clé)).additionner(((Monome)ce.getMonomes().get(clé)));
		}
	}
	
	/**
	 * Permet de trouver la variable inconnue à sortir de la contrainte. Puis sort cette variable de la contrainte. Puis 
	 * ajoute la variable de la contrainte dans la contrainte. (x1 = 4-2x3-8x2 ->rentrerBase(x3) -> x3=2-4x2-(1/2)x1 )
	 * @param String inconnue l'inconnue ("x1" par exemple) à sortir de la base
	 * 
	 */
	public void rentrerBase(String inconnue) {
		Iterator i = monomes.keySet().iterator();
		while(i.hasNext()) { // On parcours notre contrainte
			String clé = (String) i.next();
			if (clé.equals(inconnue)) { // on tombe sur le bon Xi
				Fraction coeff = ((Monome)monomes.get(clé)).getCoefficient(); // on récupère le coefficient du monome à switch
				//String tmp = this.nom;
				Monome switched = new Monome(new Fraction(-1,1),this.nom); // on met -1 car on le switch donc son coeff devient négatif. Il va ensuite être divisé par le coeff de m
				this.nom = ((Monome)monomes.get(clé)).getInconnue(); // On remplace le nom de la contrainte par l'inconnue de m
				monomes.remove(clé);
				monomes.put(switched.getInconnue(),switched);
				division(coeff); //ADD FRACTION
				break;
			}
		}
	}
	
	/**
	 * Permet de remplacer une inconnue par une contrainte explicite, puis d'additionner les monomes de même inconnue.
	 * @param ce Une autre contrainte explicite
	 * @param inconnue l'inconnue à échanger avec ce
	 */
	public void echanger(ContrainteExplicite ce, String inconnue) {
		Monome aEchanger = ((Monome)monomes.get(inconnue));
		Fraction coeff = aEchanger.getCoefficient(); //FRACTION
		monomes.remove(inconnue);
		
		for (Iterator i = ce.getMonomes().keySet().iterator(); i.hasNext();) {
			String clé = (String) i.next();
			Monome temp = new Monome(((Monome) ce.getMonomes().get(clé)).getCoefficient().FMultiplication(coeff), ((Monome) ce.getMonomes().get(clé)).getInconnue());
			if(monomes.get(clé)!=null) {
				((Monome)monomes.get(clé)).additionner(temp);
			}
			else {
				Monome ajout = new Monome(coeff.FMultiplication(((Monome)ce.getMonomes().get(clé)).getCoefficient()), clé);
				monomes.put(clé, ajout);
			}
			
		}
		
	}
	
	/**
	 * Calcul le majorant à partir de la constante et du coefficient de l'inconnue fourni en paramètre
	 * @param String inconnue
	 * @return renvoie la valeur absolue de la constante divisée par le coefficient
	 */
	public double majorant(String inconnue) {
		double constante = ((Monome)monomes.get(" ")).getCoefficient().FMath();
		double coeffInconnue = ((Monome)monomes.get(inconnue)).getCoefficient().FMath();
		return Math.abs(constante/coeffInconnue);
		
		
	}
	/**
	 * Divise tous les monomes de la contrainte this par la Fraction coeff
	 * @param Fraction coeff
	 */
	public void division(Fraction coeff) { //ADD FRACTION
		Iterator i = monomes.keySet().iterator();
		coeff.setNumerateur(-coeff.getNumerateur());
		while(i.hasNext()) {
			String clé = (String) i.next();
			((Monome) monomes.get(clé)).setCoefficient(((Monome) monomes.get(clé)).getCoefficient().FDivision(coeff)); 
		}
	}
	
	/**
	 * Renvoie le champs nom de this
	 * @return String nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Définie le champs nom de this avec la String fournit en paramètre
	 * @param String nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Renvoie le champs monomes de this, qui est une Map de Monome
	 * @return Monome monomes
	 */

	public Map getMonomes() {
		return monomes;
	}

	/**
	 * Définie le champs monomes de this avec la Map fournit en paramètre
	 * @param Map monomes
	 */
	public void setMonomes(Map monomes) {
		this.monomes = monomes;
	}
	
	/**
	 * Renvoie le champs inferieurA de this, qui est une Fraction
	 * @return Fraction inferieurA
	 */
	public Fraction getInferieurA() {
		return inferieurA;
	}

	/**
	 * Définie le champs inferieurA de this avec la Fraction fournit en paramètre
	 * @param Fraction inferieurA
	 */
	public void setInferieurA(Fraction inferieurA) {
		this.inferieurA = inferieurA;
	}
	/**
	 * Renvoie le champs nombreInconnues de this 
	 * @return int nombreInconnues
	 */
	public int getNombreInconnues() {
		return nombreInconnues;
	}
	/**
	 * Définie le champs nombreInconnues de this avec l'int fournit en paramètre
	 * @param int nombreInconnues
	 */
	public void setNombreInconnues(int nombreInconnues) {
		this.nombreInconnues = nombreInconnues;
	}
	
}