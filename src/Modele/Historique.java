package Modele;

import java.io.Serializable;
import java.util.LinkedList;

import Modele.Simplexe;

public class Historique implements Serializable{
	
	private LinkedList <Simplexe> listeSimplexe;
	
	/**
	 * Renvoie le champs LinkedList<Simplexe> listeSimplexe de this
	 * @return LinkedList<Simplexe> listeSimplexe
	 */
	public LinkedList<Simplexe> getListeSimplexe() {
		return listeSimplexe;
	}
	/**
	 * Ajoute un Simplexe à la LinkedList<Simplexe> de this
	 * @param Simplexe simplexe
	 */
	public void add(Simplexe simplexe) {
		listeSimplexe.add(simplexe);
	}
	
	/**
	 * Parcourt la liste de Simplexe et les ajoute à la String chaineFinale
	 *@return String chaineFinale
	 */
	public String toString() {
		String chaineFinale= new String();
		for(int i=0;i<listeSimplexe.size();i++) {
			chaineFinale+=listeSimplexe.get(i).toString2() +"\n";
		}
		return chaineFinale;
	}

	/**
	 * Défini le champ listeSimplexe de this avec la LinkedList<Simplexe> fournie en paramètre
	 * @param LinkedList<Simplexe>listeSimplexe
	 */
	public void setListeSimplexe(LinkedList<Simplexe> listeSimplexe) {
		this.listeSimplexe = listeSimplexe;
	}
	
	/**
	 * Supprime le dernier Simplexe de la liste de Simplexe listeSimplexe (si il y a plus d'un Simplexe)
	 */
	public void etapePrecedente() {
		if(listeSimplexe.size()>1) {
			listeSimplexe.removeLast();
		}
		
	}
	
	/**
	 * Construit un objet Historique à partir d'une liste de Simplexe LinkedList<Simplexe>
	 * @param LinkedList<Simplexe>liste
	 */
	public Historique(LinkedList<Simplexe> liste) {
		listeSimplexe = liste;
	}
	
	/**
	 * Construit un objet Historique à partir d'un autre objet Historique donné en paramètre
	 * @param Historique histo
	 */
	public Historique(Historique histo) {
		listeSimplexe=new LinkedList<Simplexe>();
		
		for(int i=0;i<histo.listeSimplexe.size();i++) {
			listeSimplexe.add(new Simplexe(histo.listeSimplexe.get(i)));
		}
	}
	
	/**
	 * Construit un objet Historique contenant une LinkedList<Simplexe>
	 */
	public Historique() {
		listeSimplexe = new LinkedList<Simplexe>();
	}
	
}
