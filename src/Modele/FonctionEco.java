package Modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FonctionEco implements Serializable{
	Map<String, Monome> monomes;
	
	/**
	 * Construit un objet FonctionEco contenant une HashMap
	 */
	public FonctionEco() {
		monomes = new HashMap<String, Monome>(); //changer en map
	}
	
	/**
	 * Construit un objet FonctionEco à partir d'un autre objet FonctionEco donné en paramètre et copie son champs Map monomes
	 * dans le champs Map monomes du nouvel objet à construire
	 * @param FonctionEco fonctionEco
	 */
	public FonctionEco(FonctionEco fonctionEco) {
		monomes = new HashMap<String, Monome>();
		for (Iterator<String> i = fonctionEco.monomes.keySet().iterator(); i.hasNext(); ) {
			String clé = i.next();
			this.ajouterMonome(new Monome((Monome)fonctionEco.monomes.get(clé)));;
		}
	}
	/**
	 * Ajoute un Monome à la Map de monome de this
	 * @param Monome m
	 */
	public void ajouterMonome(Monome m) {
		monomes.put(m.getInconnue(), m); // transformer pour que ça s'adapte à la map
	}
	
	/**
	 * Renvoie une chaine de caractère correspondant à la fonction économique
	 *@return String chaineFinale
	 */
	public String toString() {
		String chaineFinale = new String();
		chaineFinale +=  "z = ";
		Iterator<String> i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String clé = i.next();
			chaineFinale+=monomes.get(clé).toString();
		}
		
		while(i.hasNext()) {
			String clé = i.next();
			if(monomes.get(clé).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" +";
			}
			chaineFinale += " " + monomes.get(clé).toString();
		}
		return chaineFinale;

	}
	/**
	 * Echange la variable "inconnue" de la fonction économique avec une contrainte explicite, puis simplifie l'expression en additionnant
	 * les monomes de même inconnue.
	 * @param ContrainteExplicite ce
	 * @param String inconnue
	 */
	public void echanger(ContrainteExplicite ce, String inconnue) {
		Monome aEchanger = monomes.get(inconnue);
		Fraction coeff = aEchanger.getCoefficient(); //FRACTION
		monomes.remove(inconnue);
		
		for (Iterator<?> i = ce.getMonomes().keySet().iterator(); i.hasNext();) {
			String clé = (String) i.next();
			Monome temp = new Monome(((Monome) ce.getMonomes().get(clé)).getCoefficient().FMultiplication(coeff), ((Monome) ce.getMonomes().get(clé)).getInconnue());
			if(monomes.get(clé)!=null) {
				monomes.get(clé).additionner(temp);
			}
			else {
				Monome ajout = new Monome(coeff.FMultiplication(((Monome)ce.getMonomes().get(clé)).getCoefficient()), clé);
				monomes.put(clé, ajout);
			}
			
		}
		
	}
	/**
	 * Renvoie le Monome de la fonction économique ayant la valeur de coefficient maximale 
	 * @return String res
	 */
	public String monomeCoeffMax() {
		Fraction max = new Fraction(0);
		String res=new String();
		
		for (Iterator<String> i = monomes.keySet().iterator(); i.hasNext();) {
			String clé = i.next();
			if(monomes.get(clé).getCoefficient().FSup(max) && !monomes.get(clé).getInconnue().equals(" ")) {
				max=new Fraction(monomes.get(clé).getCoefficient());
				res=monomes.get(clé).getInconnue();
			}
			
		}
		return res;
		
	}
	/**
	 * Renvoie le champs monomes (Map) de this
	 * @return Map monomes
	 */
	public Map<String, Monome> getMonomes() {
		return monomes;
	}
}