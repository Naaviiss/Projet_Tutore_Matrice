package Modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ContrainteExplicite implements Serializable{
	/**
	 * 
	 */
	String nom;
	Map<String, Monome> monomes;
	Fraction inferieurA;
	int nombreInconnues;
	
	/**
	 * Construit un objet ContrainteExplicite et remplit les champs Fraction inferieurA et String nom avec les variables
	 * donn�es en param�tre ( les autres champs sont une HashMap vide et un nombre d'inconnues initialis� � 0)
	 * @param Fraction limite
	 * @param String nom
	 */
	public ContrainteExplicite(Fraction limite, String nom){
		this.monomes = new HashMap<String, Monome>();
		inferieurA=limite;
		nombreInconnues=0;
		this.nom=nom;
	}
	
	/**
	 * Construit un objet ContrainteExplicite � partir d'un autre objet ContrainteExplicite donn� en param�tre et copie 
	 * tous les champs de la ContrainteExplicite en param�tre dans le nouvel objet qui sera construit
	 * @param ContrainteExplicite ce
	 */
	public ContrainteExplicite(ContrainteExplicite ce) {
		this.nom = ce.nom;
		this.inferieurA = new Fraction(ce.inferieurA);
		this.nombreInconnues = ce.nombreInconnues;
		monomes = new HashMap<String, Monome>();
		for (Iterator<?> i = ce.monomes.keySet().iterator(); i.hasNext(); ) {
			String cl� = (String) i.next();
			this.ajouterMonome(new Monome((Monome)ce.monomes.get(cl�)));;
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
		for (Iterator<String> i = monomes.keySet().iterator(); i.hasNext(); ) {
			String cl� = i.next();
			monomes.get(cl�).setCoefficient((monomes.get(cl�).getCoefficient().FMultiplication(new Fraction(-1,1))));
		}
		Monome m = new Monome(inferieurA, " ");
		this.ajouterMonome(m);
	}
	
	/* toString pour le dictionnaire 0 (sans les "=", seulement les contraintes avec les "<=")*/
	
	/**
	 * Affiche le premier dictionnaire qui correspond au dictionnaire ou chaque contrainte doit �tre inferieure � une limite
	 * @return String chaineFinale 
	 * 
	 */
	
	public String toStringDico1() {
		String chaineFinale = new String();
		Iterator<String> i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String cl� = i.next();
			chaineFinale+=monomes.get(cl�).toString();
		}
		
		while(i.hasNext()) {
			String cl� = i.next();
			if(monomes.get(cl�).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" + ";
			}
			chaineFinale += monomes.get(cl�).toString();
		}
		chaineFinale+= " <= "+ this.inferieurA;
		return chaineFinale;
	}
	
	/* toString pour les autres dictionnaires (avec les "=")*/
	
	/**
	 * @return String chaineFinale: renvoie une chaine de caract�res correspondant � un dictionnaire ( autre que le dictionnaire n�1)
	 * Permet l'affichage des autres dictionnaires(autre que le dictionnaire n�1)
	 */
	public String toString() {
		String chaineFinale = new String();
		chaineFinale +=  this.nom + " = ";
		Iterator<String> i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String cl� = i.next();
			chaineFinale+=monomes.get(cl�).toString();
		}
		
		while(i.hasNext()) {
			String cl� = i.next();
			if(monomes.get(cl�).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" +";
			}
			chaineFinale += " " + monomes.get(cl�).toString();
		}
		return chaineFinale;

	}
	
	/*Parcourt la HashMap <nom de l'inconnue, Monome> de this. Additionne chaque Monome � celui ayant la m�me cl� dans la HashMap de la 
	 * Contrainte explicite en param�tre
	 */
	
	/**
	 * 
	 * @param ContrainteExplicite ce: renvoie une contrainte explicite
	 * Parcourt la HashMap<nom de l'inconnue,Monome> de this et additionne chaque Monome � celui ayant la m�me cl� dans la HashMap
	 * de ContrainteExplicite donn�e en param�tre
	 */
	public void additionnerLigne(ContrainteExplicite ce) {
		Iterator<String> i = monomes.keySet().iterator(); 
		while(i.hasNext()) {
			String cl� = i.next();
			monomes.get(cl�).additionner(ce.getMonomes().get(cl�));
		}
	}
	
	/**
	 * Permet de trouver la variable inconnue � sortir de la contrainte. Puis sort cette variable de la contrainte. Puis 
	 * ajoute la variable de la contrainte dans la contrainte. (x1 = 4-2x3-8x2 ->rentrerBase(x3) -> x3=2-4x2-(1/2)x1 )
	 * @param String inconnue l'inconnue ("x1" par exemple) � sortir de la base
	 * 
	 */
	public void rentrerBase(String inconnue) {
		Iterator<String> i = monomes.keySet().iterator();
		while(i.hasNext()) { // On parcours notre contrainte
			String cl� = i.next();
			if (cl�.equals(inconnue)) { // on tombe sur le bon Xi
				Fraction coeff = monomes.get(cl�).getCoefficient(); // on r�cup�re le coefficient du monome � switch
				//String tmp = this.nom;
				Monome switched = new Monome(new Fraction(-1,1),this.nom); // on met -1 car on le switch donc son coeff devient n�gatif. Il va ensuite �tre divis� par le coeff de m
				this.nom = monomes.get(cl�).getInconnue(); // On remplace le nom de la contrainte par l'inconnue de m
				monomes.remove(cl�);
				monomes.put(switched.getInconnue(),switched);
				division(coeff); //ADD FRACTION
				break;
			}
		}
	}
	
	/**
	 * Permet de remplacer une inconnue par une contrainte explicite, puis d'additionner les monomes de m�me inconnue.
	 * @param ce Une autre contrainte explicite
	 * @param inconnue l'inconnue � �changer avec ce
	 */
	public void echanger(ContrainteExplicite ce, String inconnue) {
		Monome aEchanger = monomes.get(inconnue);
		Fraction coeff = aEchanger.getCoefficient(); //FRACTION
		monomes.remove(inconnue);
		
		for (Iterator<String> i = ce.getMonomes().keySet().iterator(); i.hasNext();) {
			String cl� = i.next();
			Monome temp = new Monome(ce.getMonomes().get(cl�).getCoefficient().FMultiplication(coeff), ce.getMonomes().get(cl�).getInconnue());
			if(monomes.get(cl�)!=null) {
				monomes.get(cl�).additionner(temp);
			}
			else {
				Monome ajout = new Monome(coeff.FMultiplication(ce.getMonomes().get(cl�).getCoefficient()), cl�);
				monomes.put(cl�, ajout);
			}
			
		}
		
	}
	
	/**
	 * Calcul le majorant � partir de la constante et du coefficient de l'inconnue fourni en param�tre
	 * @param String inconnue
	 * @return renvoie la valeur absolue de la constante divis�e par le coefficient
	 */
	public double majorant(String inconnue) {
		double constante = monomes.get(" ").getCoefficient().FMath();
		double coeffInconnue = monomes.get(inconnue).getCoefficient().FMath();
		return Math.abs(constante/coeffInconnue);
		
		
	}
	/**
	 * Divise tous les monomes de la contrainte this par la Fraction coeff
	 * @param Fraction coeff
	 */
	public void division(Fraction coeff) { //ADD FRACTION
		Iterator<String> i = monomes.keySet().iterator();
		coeff.setNumerateur(-coeff.getNumerateur());
		while(i.hasNext()) {
			String cl� = i.next();
			monomes.get(cl�).setCoefficient(monomes.get(cl�).getCoefficient().FDivision(coeff)); 
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
	 * D�finie le champs nom de this avec la String fournit en param�tre
	 * @param String nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Renvoie le champs monomes de this, qui est une Map de Monome
	 * @return Monome monomes
	 */

	public Map<String, Monome> getMonomes() {
		return monomes;
	}

	/**
	 * D�finie le champs monomes de this avec la Map fournit en param�tre
	 * @param Map monomes
	 */
	public void setMonomes(Map<String, Monome> monomes) {
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
	 * D�finie le champs inferieurA de this avec la Fraction fournit en param�tre
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
	 * D�finie le champs nombreInconnues de this avec l'int fournit en param�tre
	 * @param int nombreInconnues
	 */
	public void setNombreInconnues(int nombreInconnues) {
		this.nombreInconnues = nombreInconnues;
	}
	
}