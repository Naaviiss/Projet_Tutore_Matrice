package modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FonctionEco implements Serializable{
	Map monomes;
	
	/**
	 * Construit un objet FonctionEco contenant une HashMap
	 */
	public FonctionEco() {
		monomes = new HashMap(); //changer en map
	}
	
	/**
	 * Construit un objet FonctionEco à partir d'un autre objet FonctionEco donné en paramètre et copie son champs Map monomes
	 * dans le champs Map monomes du nouvel objet à construire
	 * @param FonctionEco fonctionEco
	 */
	public FonctionEco(FonctionEco fonctionEco) {
		monomes = new HashMap();
		for (Iterator i = fonctionEco.monomes.keySet().iterator(); i.hasNext(); ) {
			String clé = (String) i.next();
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
	/**
	 * Echange la variable "inconnue" de la fonction économique avec une contrainte explicite, puis simplifie l'expression en additionnant
	 * les monomes de même inconnue.
	 * @param ContrainteExplicite ce
	 * @param String inconnue
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
	 * Renvoie le Monome de la fonction économique ayant la valeur de coefficient maximale 
	 * @return String res
	 */
	public String monomeCoeffMax() {
		Fraction max = new Fraction(0);
		String res=new String();
		
		for (Iterator i = monomes.keySet().iterator(); i.hasNext();) {
			String clé = (String) i.next();
			if(((Monome)monomes.get(clé)).getCoefficient().FSup(max) && !((Monome)monomes.get(clé)).getInconnue().equals(" ")) {
				max=new Fraction(((Monome)monomes.get(clé)).getCoefficient());
				res=((Monome)monomes.get(clé)).getInconnue();
			}
			
		}
		return res;
		
	}
	/**
	 * Renvoie le champs monomes (Map) de this
	 * @return Map monomes
	 */
	public Map getMonomes() {
		return monomes;
	}
}