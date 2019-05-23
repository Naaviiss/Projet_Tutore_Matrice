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
			String cle = i.next();
			this.ajouterMonome(new Monome((Monome)fonctionEco.monomes.get(cle)));;
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
			String cle = i.next();
			chaineFinale+=monomes.get(cle).toString();
		}
		
		while(i.hasNext()) {
			String cle = i.next();
			if(monomes.get(cle).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" +";
			}
			chaineFinale += " " + monomes.get(cle).toString();
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
			String cle = (String) i.next();
			Monome temp = new Monome(((Monome) ce.getMonomes().get(cle)).getCoefficient().FMultiplication(coeff), ((Monome) ce.getMonomes().get(cle)).getInconnue());
			if(monomes.get(cle)!=null) {
				monomes.get(cle).additionner(temp);
			}
			else {
				Monome ajout = new Monome(coeff.FMultiplication(((Monome)ce.getMonomes().get(cle)).getCoefficient()), cle);
				monomes.put(cle, ajout);
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
			String cle = i.next();
			if(monomes.get(cle).getCoefficient().FSup(max) && !monomes.get(cle).getInconnue().equals(" ")) {
				max=new Fraction(monomes.get(cle).getCoefficient());
				res=monomes.get(cle).getInconnue();
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