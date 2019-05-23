package Modele;

import java.io.Serializable;

/**
 * Fraction est une classe qui permet la gestion de tout ce qui concerne les
 * fractions
 */
public class Fraction implements Serializable {
	/**
	 * Le numérateur de la fraction
	 */
	private int numerateur;

	/**
	 * Le dénominateur de la fraction
	 */
	private int denominateur;

	/**
	 * Constructeur par défaut de la classe Fraction Besoin d'un numérateur et d'un
	 * dénominateur
	 * 
	 * @param parNum
	 *            un numérateur
	 * @param parDen
	 *            un dénominateur
	 */
	public Fraction(int parNum, int parDen) {
		numerateur = parNum;
		denominateur = parDen;
		reduire();
	}

	/**
	 * Autre constructeur par défaut de la classe Fraction Besoin d'une seule valeur
	 * car c'est un entier.
	 * 
	 * @param parVal
	 *            un nombre
	 */
	public Fraction(int parVal) {
		numerateur = parVal;
		denominateur = 1;
		reduire();
	}

	/**
	 * Autre constructeur par défaut de la classe Fraction Besoin d'une fraction car
	 * elle effectue une copie de fraction
	 * 
	 * @param parFrac
	 *            une fraction
	 */
	public Fraction(Fraction parFrac) {
		numerateur = parFrac.getNumerateur();
		denominateur = parFrac.getDenominateur();
		reduire();
	}

	/**
	 * Autre constructeur par défaut de la classe Fraction Change un string en
	 * fraction (3/5 devient Fraction(3,5))
	 * 
	 * @param parFrac
	 *            une fraction
	 * @throws ExceptEntreFraction
	 * @throws ExceptZeroDivision
	 * @throws ExceptNegatifMalPlace
	 * @throws ExceptCaseVide
	 */
	public Fraction(String parFrac)
			throws ExceptEntreFraction, ExceptZeroDivision, ExceptNegatifMalPlace, ExceptCaseVide {
		int slash = 0; // si il y a un slash dans le String
		int rencontre = 0; // savoir quand on a passé le slash
		String numerateurString = "";
		String denominateurString = "";
		for (char ch : parFrac.toCharArray()) { // Test s'il y a un slash dans le String
			if (ch == '/') {
				slash = 1;
			}
		}

		if (slash == 1) { // si c'est une fraction
			for (char ch : parFrac.toCharArray()) {
				if (ch == '/')
					rencontre = 1;
				else if (ch != '/' && rencontre == 0) {
					if (ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6'
							&& ch != '7' && ch != '8' && ch != '9' && ch != '-') {
						throw new ExceptEntreFraction(); // s'il y a une lettre ou un caractere spécial dans la fraction
					} else
						numerateurString += ch;
				} else {
					if (ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6'
							&& ch != '7' && ch != '8' && ch != '9' && ch != '-') {
						System.out.println("la");
						throw new ExceptEntreFraction(); // s'il y a une lettre ou un caractere spécial dans la fraction
					} else
						denominateurString += ch;
				}
			}
			if (numerateurString == "")
				throw new ExceptCaseVide();
			if (denominateurString == "")
				throw new ExceptCaseVide();
			if (numerateurString.equals("-"))
				throw new ExceptCaseVide();
			if (denominateurString.equals("-"))
				throw new ExceptCaseVide();
			if (Integer.parseInt(denominateurString) == 0)
				throw new ExceptZeroDivision();
			for (int i = 1; i < numerateurString.length(); i++) {
				if (numerateurString.charAt(i) == '-') {
					throw new ExceptNegatifMalPlace();
				}
			}
			for (int i = 1; i < denominateurString.length(); i++) {
				if (denominateurString.charAt(i) == '-') {
					throw new ExceptNegatifMalPlace();
				}
			}
			numerateur = Integer.parseInt(numerateurString);
			denominateur = Integer.parseInt(denominateurString);
		} else {// si c'est un entier
			for (char ch : parFrac.toCharArray()) {
				if (ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7'
						&& ch != '8' && ch != '9' && ch != '-') {
					System.out.println("ici");
					throw new ExceptEntreFraction(); // s'il y a une lettre ou un caractere spécial dans la case
				} else
					numerateurString += ch;
			}
			if (numerateurString == "")
				throw new ExceptCaseVide();
			if (numerateurString.equals("-"))
				throw new ExceptCaseVide();
			for (int i = 1; i < numerateurString.length(); i++) {
				if (numerateurString.charAt(i) == '-') {
					throw new ExceptNegatifMalPlace();
				}
			}
			numerateur = Integer.parseInt(numerateurString);
			denominateur = 1;
		}
		reduire();
	}

	/**
	 * Renvoie vrai si la string est une fraction
	 * 
	 * @param parFrac
	 *            une fraction
	 * @return vrai si la string est une fraction, sinon faux.
	 */
	public static boolean isFraction(String parFrac) {
		int slash = 0; // s'il y a un slash dans le String
		int rencontre = 0; // savoir quand on a passé le slash
		String numerateurString = "";
		String denominateurString = "";
		for (char ch : parFrac.toCharArray()) { // Test si il y a un slash dans le String
			if (ch == '/') {
				slash = 1;
			}
		}
		if (slash == 1) {
			for (char ch : parFrac.toCharArray()) {
				if (ch == '/') {
					rencontre = 1;
				} else if (ch != '/' && rencontre == 0) {
					if (ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6'
							&& ch != '7' && ch != '8' && ch != '9' && ch != '/') {
						return false;
					} else
						numerateurString += ch;
				} else {
					if (ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6'
							&& ch != '7' && ch != '8' && ch != '9' && ch != '/') {
						return false;
					} else
						denominateurString += ch;
				}
			}
			if (numerateurString == "" || denominateurString == "")
				return false;
			if (Integer.parseInt(denominateurString) == 0)
				return false;
		} else {
			for (char ch : parFrac.toCharArray()) {
				if (ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7'
						&& ch != '8' && ch != '9') {
					return false;
				} else
					numerateurString += ch;
			}
			if (numerateurString == "")
				return false;
		}
		return true;
	}

	/**
	 * Rend le numérateur de la fraction
	 * 
	 * @return le numérateur de la fraction
	 */
	public int getNumerateur() {
		return numerateur;
	}

	/**
	 * Rend le dénominateur de la fraction
	 * 
	 * @return le dénominateur de la fraction
	 */
	public int getDenominateur() {
		return denominateur;
	}

	// SETTER (ne pas oublier de recreer la fraction apres pour qu'elle soit
	// réduite)
	/**
	 * Change le numérateur de la fraction
	 * 
	 * @param parNum
	 *            le nouveau numérateur de la fraction
	 */
	public void setNumerateur(int parNum) {
		this.numerateur = parNum;
	}

	/**
	 * Change le dénominateur de la fraction
	 * 
	 * @param parDen
	 *            le nouveau dénominateur de la fraction
	 */
	public void setDenominateur(int parDen) {
		this.denominateur = parDen;
	}

	/**
	 * Calcule le PGCD entre deux nombres
	 * 
	 * @param Num
	 *            le numérateur de la fraction
	 * @param Den
	 *            lé dénominateur de la fraction
	 * @return une fraction irreductible
	 */
	public int CalculPGCD(int Num, int Den) {
		if (Num % Den == 0) {
			return Den;
		}
		return CalculPGCD(Den, Num % Den);
	}

	/**
	 * Reduit une fraction de façon irréductible
	 */
	public void reduire() {
		if (denominateur < 0) { // pour mettre le - devant
			numerateur *= -1;
			denominateur *= -1;
		}
		int pgcd = Math.abs(CalculPGCD(numerateur, denominateur));
		this.setNumerateur(numerateur / pgcd);
		this.setDenominateur(denominateur / pgcd);
	}

	/**
	 * Reduit une fration de façon irréductible mais pour l'affichage
	 * 
	 * @param frac
	 *            une fraction
	 * @return une fraction irréductible pour l'affichage
	 */
	public Fraction reduireV2(Fraction frac) {
		// reduit la fraction jusqu'a ce qu'elle soit irreductible -- ne sert a rien si
		// "reduire()" est activé dans les constructeur "Fraction"
		int pgcd = CalculPGCD(frac.getNumerateur(), frac.getDenominateur());
		frac.setNumerateur(frac.getNumerateur() / pgcd);
		frac.setDenominateur(frac.getDenominateur() / pgcd);
		return new Fraction(frac.getNumerateur(), frac.getDenominateur());
	}

	/**
	 * Ecrit une fraction sous format décimal.
	 * 
	 * @return une fraction sous le format décimal
	 */
	public double FMath() {
		double nume = this.getNumerateur();
		double deno = this.getDenominateur();
		double resultat = nume / deno;
		return resultat;
	}

	/**
	 * Compare deux fractions entre elles
	 * 
	 * @param f
	 *            une fraction
	 * @return vrai si elles sont identiques, faux sinon.
	 */
	public boolean FCompare(Fraction f) {
		if ((this.getNumerateur() == f.getNumerateur()) && (this.getDenominateur() == f.getDenominateur()))
			return true;
		else
			return false;
	}

	/**
	 * Renvoie si la fraction est plus grande qu'une autre.
	 * 
	 * @param f
	 *            une fraction
	 * @return vrai si la fraction est plus grande, sinon faux.
	 */
	public boolean FSup(Fraction f) {
		if (this.FMath() > f.FMath())
			return true;
		else
			return false;
	}

	/**
	 * Additionne deux fractions entre elles
	 * 
	 * @param frac
	 *            une fraction
	 * @return l'addition des deux fractions
	 */
	public Fraction FAddition(Fraction frac) {
		int num = ((numerateur * frac.getDenominateur()) + (frac.getNumerateur() * denominateur));
		int den = denominateur * frac.getDenominateur();
		return new Fraction(num, den);
	}

	/**
	 * Soustrait deux fractions entre elles
	 * 
	 * @param frac
	 *            une fraction
	 * @return la soustraction des deux fractions
	 */
	public Fraction FSoustraction(Fraction frac) {
		int num = ((numerateur * frac.denominateur) - (frac.numerateur * denominateur));
		int den = denominateur * frac.denominateur;
		return new Fraction(num, den);
	}

	/**
	 * Multiplie deux fractions entre elles
	 * 
	 * @param frac
	 *            une fraction
	 * @return la multiplication des deux fractions
	 */
	public Fraction FMultiplication(Fraction frac) {
		int num = numerateur * frac.getNumerateur();
		int den = denominateur * frac.getDenominateur();
		return new Fraction(num, den);
	}

	/**
	 * Divise deux fractions entre elles
	 * 
	 * @param frac
	 *            une fraction
	 * @return la division des deux fractions
	 */
	public Fraction FDivision(Fraction frac) {
		int num = numerateur * frac.getDenominateur();
		int den = denominateur * frac.getNumerateur();
		return new Fraction(num, den);
	}

	/**
	 * Affichage d'une fraction
	 * 
	 * @return l'affichage d'une fraction sous le format d'un string
	 */
	public String toStringHoriz() {
		if (denominateur == 1) {
			return Integer.toString(numerateur);
		}
		int t1 = String.valueOf(numerateur).length();
		int t2 = String.valueOf(denominateur).length();
		String tirets = "-";
		String espaces = " ";
		if ((t1 >= 2) && (t2 >= 2)) {
			if (t1 == t2) {
				for (int i = 0; i < t2 - 1; i++) {
					tirets = tirets.concat("-");
				}
				return numerateur + "\n" + tirets + "\n" + denominateur;
			} else if (t1 < t2) {
				int espace = (t2 / t1) - 2;
				for (int i = 0; i < espace; i++) {
					espaces = espaces.concat(" ");
				}
				for (int i = 0; i < t2 - 1; i++) {
					tirets = tirets.concat("-");
				}
				return espaces + numerateur + "\n" + tirets + "\n" + denominateur;
			} else {
				int espace = (t1 / t2) - 2;
				for (int i = 0; i < espace; i++) {
					espaces = espaces.concat(" ");
				}
				for (int i = 0; i < t1 - 1; i++) {
					tirets = tirets.concat("-");
				}
				return numerateur + "\n" + tirets + "\n" + espaces + denominateur;
			}
		} else {
			espaces = "";
			if (t1 < t2) {
				int espace = (t2 / t1) - 2;
				for (int i = 0; i < espace; i++) {
					espaces = espaces.concat(" ");
				}
				for (int i = 0; i < t2 - 1; i++) {
					tirets = tirets.concat("-");
				}
				return espaces + numerateur + "\n" + tirets + "\n" + denominateur;
			} else {
				int espace = (t1 / t2) - 2;
				for (int i = 0; i < espace; i++) {
					espaces = espaces.concat(" ");
				}
				for (int i = 0; i < t1 - 1; i++) {
					tirets = tirets.concat("-");
				}
				return numerateur + "\n" + tirets + "\n" + espaces + denominateur;
			}
		}
	}

	/**
	 * Affiche une fraction
	 * 
	 * @return la fraction
	 */
	@Override
	public String toString() {
		if (denominateur != 1)
			return numerateur + "/" + denominateur;
		else
			return Integer.toString(numerateur);
	}
}