package modele;

import java.io.Serializable;

public class Monome implements Serializable{
	Fraction coefficient;
	String inconnue;
	
	/**
	 * Construit un objet Monome à partir d'une Fraction correspondant au coefficient du monome et d'une String correspondant
	 * au nom de l'inconnue
	 * @param Fraction coeff
	 * @param String inconnue
	 */
	public Monome(Fraction coeff, String inconnue) {
		coefficient=coeff;
		this.inconnue=inconnue;
	}
	
	/**
	 * Construit un objet Monome à partir d'un autre objet Monome et copie tous ses champs dans les champs du nouvel objet à construire
	 * @param Monome monome
	 */
	public Monome(Monome monome) {
		coefficient=new Fraction(monome.getCoefficient());
		inconnue=monome.getInconnue();
	}

	/**
	 * Affiche le Monome et gère l'affichage des cas particuliers
	 *@return String 
	 */
	public String toString() {
		if(coefficient.toString().equals("1") && inconnue.equals(" ")==false) {
			return inconnue;
		}
		
		if(coefficient.toString().equals("-1") && inconnue.equals(" ")==false) {
			return "-"+inconnue;
		}
		
		else if(coefficient.toString().equals("0")) {
			return "";
		}
		else {
			return coefficient + inconnue;
		}
		
		
	}
	
	/**
	 * Additionne deux Monomes en ajoutant leur coefficient
	 * @param Monome m
	 */
	public void additionner(Monome m) {
		this.coefficient = this.coefficient.FAddition(m.getCoefficient());
		
	}
	
	/**
	 * Multiplie deux Monomes en multipliant leur coefficient
	 * @param constante
	 */
	public void multiplier(Fraction constante) { // ADD FRACTION
		this.coefficient.FMultiplication(constante);
	}
	
	/**
	 * Renvoie le champ coefficient(Fraction) de this 
	 * @return Fraction coefficient
	 */
	public Fraction getCoefficient() {
		return coefficient;
	}

	/**
	 * Definie le champ coefficient de this avec la Fraction fournie en paramètre
	 * @param Fraction coefficient
	 */
	public void setCoefficient(Fraction coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * Renvoie le champ inconnue(String) de this
	 * @return String inconnue
	 */
	public String getInconnue() {
		return inconnue;
	}
	/**
	 * Définie le champ inconnue de this avec la String fournie en paramètre
	 * @param String inconnue
	 */
	public void setInconnue(String inconnue) {
		this.inconnue = inconnue;
	}
}