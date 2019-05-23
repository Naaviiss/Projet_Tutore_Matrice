package Modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Simplexe implements Serializable{
	List<ContrainteExplicite> contraintes;
	FonctionEco fonctionEco;
	
	/**
	 * Construit un objet Simplexe à partir d'une List et d'une FonctionEco fournie en paramètre
	 * @param List contraintes
	 * @param FonctionEco fonctionEco
	 */
	public Simplexe(List<ContrainteExplicite> contraintes, FonctionEco fonctionEco) {
		this.contraintes=contraintes;
		this.fonctionEco=fonctionEco;
	}
	
	/**
	 * Construit un objet Simplexe vide (constructeur de base)
	 */
	public Simplexe() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construit un objet Simplexe à partir d'un autre Simplexe et copie tous ses champs dans les champs du nouvel objet à construire
	 * @param Simplexe simp
	 */
	public Simplexe(Simplexe simp) {
		List<ContrainteExplicite> cont = new LinkedList<ContrainteExplicite>();
		for(int i=0;i<simp.contraintes.size();i++) {
			ContrainteExplicite ce = simp.contraintes.get(i);
			cont.add(new ContrainteExplicite(ce));
		}
		contraintes=cont;
		
		fonctionEco=new FonctionEco(simp.getFonctionEco());
	}

	/**
	 * Permet le passage au dictionnaire n°1 du Simplexe
	 */
	public void passageDico1() {
		for(int i = 0; i<contraintes.size();i++) {
			contraintes.get(i).passageDico1();
		}
	}
	
	
	/**
	 * Remplie la String chaineFinale avec les différentes ContraintesExplicite et renvoie la chaineFinale.Cette fonction permet
	 * un affichage en HTML
	 *@return String chaineFinale
	 */
	public String toString() {
		String chaineFinale="";
		for(int i=0; i<contraintes.size(); i++) {
			chaineFinale += "<p>"+contraintes.get(i).toString()+"</p><br>";
		}
		chaineFinale+= "<p>"+fonctionEco.toString()+"</p><br><hr><br>";
		return chaineFinale;
	}
	
	/**
	 * Remplie la String chaineFinale avec les différentes ContraintesExplicite et renvoie la chaineFinale
	 * @return String chaineFinale
	 */
	public String toString2() {
		String chaineFinale="";
		for(int i=0; i<contraintes.size(); i++) {
			chaineFinale += contraintes.get(i).toString() + "\n";
			
		}
		chaineFinale+=fonctionEco.toString();
		return chaineFinale;
	}
	
	/**
	 * Utilisée lors des indices qui sont demandés par l'utilisateur ,cette fonction affiche quel est l'échange de variable dans la base
	 * et hors base qu'il est préférable d'effectuer afin de se rapprocher au plus près du bénéfice maximal.
	 * @return String
	 */
	public String echangeJudicieux() {
		String inconnueBase = fonctionEco.monomeCoeffMax();
		String inconnueHorsBase = "";
		
		double max=100000000000000.0;
		
		for(int i=0; i<contraintes.size(); i++) {
			
			if(contraintes.get(i).getMonomes().get(inconnueBase)!=null) {
				if(contraintes.get(i).majorant(inconnueBase)<max /*&& ((ContrainteExplicite)contraintes.get(i)*/){
				
				inconnueHorsBase = contraintes.get(i).getNom();
				max=contraintes.get(i).majorant(inconnueBase);
				}
			}
			
		}
		if(inconnueHorsBase == "") {
			return "Vous avez atteint le bénéfice maximum";
		}
		return "Echange à effectuer : " + inconnueBase + " & " + inconnueHorsBase;
	}
	
	/**
	 * Permet l'échange d'une variable hors base et d'une variable dans la base dans le Simplexe
	 * @param String inconnueHorsBase
	 * @param String inconnueBase
	 */
	public void echanger(String inconnueHorsBase, String inconnueBase) {
		int mem=-1;
		ContrainteExplicite memCE=new ContrainteExplicite(new Fraction(1,1), "");
		for(int i = 0; i<contraintes.size();i++) {
			if(contraintes.get(i).getNom().equals(inconnueHorsBase)) {
				memCE = contraintes.get(i);
				memCE.rentrerBase(inconnueBase);
				fonctionEco.echanger(memCE, inconnueBase);
				mem=i;
				
			}
		}
		for(int i=0;i<contraintes.size();i++) {
			if(i != mem) {
				contraintes.get(i).echanger(memCE, inconnueBase);
			}
		}
	}

	/**
	 * Renvoie le champ contraintes(List) de this
	 * @return List contraintes
	 */
	public List<ContrainteExplicite> getContraintes() {
		return contraintes;
	}
	
	/**
	 * Défini le champ contraintes de this avec le paramètre fourni
	 * @param List contraintes
	 */
	public void setContraintes(List<ContrainteExplicite> contraintes) {
		this.contraintes = contraintes;
	}

	/**
	 * Renvoie le champ fonctionEco(FonctionEco) de this
	 * @return FonctionEco fonctionEco
	 */
	public FonctionEco getFonctionEco() {
		return fonctionEco;
	}

	/**
	 * Défini le champ fonctionEco de this avec le paramètre fourni
	 * @param FonctionEco fonctionEco
	 */
	public void setFonctionEco(FonctionEco fonctionEco) {
		this.fonctionEco = fonctionEco;
	}
	
	
}