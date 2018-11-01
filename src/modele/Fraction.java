package modele;

public class Fraction {
	private int numerateur;
	private int denominateur;
	
	//FRACTION
	//une fraction composee d'une numerateur et d'un denominateur
	public Fraction(int parNum, int parDen) {
		if(parDen == 0) {
			throw new RuntimeException("Division par zero");
		}
		numerateur = parNum;
		denominateur = parDen;
		reduire();   //A voir si on veut que la fraction soit reduite directement ou non
	}
	//si la fraction est un nombre entier
	public Fraction(int parVal) {
		numerateur = parVal;
		denominateur = 1;
		reduire();
	}
	
	//GETTER
	public int getNumerateur() {
		return numerateur;
	}
	
	public int getDenominateur() {
		return denominateur;
	}
	
	//SETTER
	public void setNumerateur(int parNum) {
		this.numerateur = parNum;
	}
	
	public void setDenominateur(int parDen) {
		this.denominateur = parDen;
	}
	
	//CALCUL PGCD
	//calcul le pgcd entre deux nombres ; utilise pour reduire une fraction
	public int CalculPGCD(int Num, int Den) {
		if(Num % Den == 0) {
			return Den;
		}
		return CalculPGCD(Den, Num % Den);
	}
	
	//REDUIRE reduit de facon definitive
	//reduit la fraction jusqu'a ce qu'elle soit irreductible
	void reduire() {
		if(denominateur < 0) { //pour mettre le - devant
			numerateur *= -1;
			denominateur *= -1;
		}
		int pgcd = Math.abs(CalculPGCD(numerateur,denominateur));
		this.setNumerateur(numerateur/pgcd);
		this.setDenominateur(denominateur/pgcd);
	}
	
	//REDUIREV2 reduit juste pour l'affichage
	//reduit la fraction jusqu'a ce qu'elle soit irreductible
	public Fraction reduireV2(Fraction frac) {
		int pgcd = CalculPGCD(frac.getNumerateur(),frac.getDenominateur());
		frac.setNumerateur(frac.getNumerateur()/pgcd);
		frac.setDenominateur(frac.getDenominateur()/pgcd);
		return new Fraction(frac.getNumerateur(), frac.getDenominateur());
	}
	
	//ECRITURE NON FRACTIONNELLE (voir si utilise float ou double)
	//ecrit une fraction sous le format a virgule (float : 7 chiffres apres la virgule || double = 15 chiffres apres la virgule)
	public double FMath(Fraction frac) {
		double nume = frac.getNumerateur();
		double deno = frac.getDenominateur();
		double resultat = nume / deno;
		return resultat;
	}
	
	//COMPARE
	//compare deux fractions entre elles
	public boolean FCompare(Fraction f) {
		if((this.getNumerateur() == f.getNumerateur()) && (this.getDenominateur() == f.getDenominateur())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//donne l'ordre de deux fractions ( < ou > )  true si sup
	public boolean FSup(Fraction f) {
		if(this.FMath(this) > f.FMath(f)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//ADDITION
	//addition deux fractions entre elles
	public Fraction FAddition(Fraction frac) {
		int num = ( (numerateur * frac.getDenominateur()) + (frac.getNumerateur() * denominateur) );
		int den = denominateur * frac.getDenominateur();
		return new Fraction(num, den);
	}
	
	//SOUSTRACTION
	//soustrait deux fractions entre elles
	public Fraction FSoustraction(Fraction frac) {
		int num = ( (numerateur * frac.denominateur) - (frac.numerateur * denominateur) );
		int den = denominateur * frac.denominateur;
		return new Fraction(num, den);
	}
	
	//MULTIPLICATION
	//multiplie deux fractions entre elles
	public Fraction FMultiplication(Fraction frac) {
		int num = numerateur * frac.getNumerateur();
		int den = denominateur * frac.getDenominateur();
		return new Fraction(num, den);
	}
	
	//DIVISION
	//divise deux fractions entre elles
	public Fraction FDivision(Fraction frac) {
		int num = numerateur * frac.getDenominateur();
		int den = denominateur * frac.getNumerateur();
		return new Fraction(num, den);
	}
	
	//TOSTRING
	//ecrit une fraction
	public String toString() {
		if(denominateur == 1) {
			return Integer.toString(numerateur);
		}
		return numerateur + "/" + denominateur;
	}
	
	//TEST_MAIN
//	public static void main(String[] args) {
//		
//		//FRACTION
//		Fraction f1 = new Fraction(30,-13);
//		System.out.println("f1 = " + f1.toString());
//		Fraction f2 = new Fraction(-2,26);
//		System.out.println("f2 = " + f2.toString());
//		System.out.println("f1 et f2 pareil ? " + f1.FCompare(f2));
//		System.out.println();
//		
//		System.out.println("f3 representera le resultat de l'operation entre f1 et f2");
//		System.out.println();
//		
//		//ADDITION
//		Fraction f3 = f1.FAddition(f2);
//		System.out.println("f3 = " + f3.toString());
//		System.out.println("Resusltat de " + f1 + " + " + f2 + " = " + f3);
//		System.out.println();
//		
//		//SOUSTRACTION
//		f3 = f1.FSoustraction(f2);
//		System.out.println("f3 = " + f3.toString());
//		System.out.println("Resusltat de " + f1 + " - " + f2 + " = " + f3);
//		System.out.println();
//		
//		//MULTIPLICATION
//		f3 = f1.FMultiplication(f2);
//		System.out.println("f3 = " + f3.toString());
//		System.out.println("Resusltat de " + f1 + " * " + f2 + " = " + f3);
//		System.out.println();
//		
//		//DIVISION
//		f3 = f1.FDivision(f2);
//		System.out.println("f3 = " + f3.toString());
//		System.out.println("Resusltat de " + f1 + " / " + f2 + " = " + f3);
//		System.out.println();
//		
//		//REDUCTION
//		Fraction f4 = new Fraction(30,10);
//		System.out.println("f4 = " + f4.toString());
//		System.out.println("Avant reduction de f4 = " + f4);
//		//AVEC UTILISATION DE REDUIRE
//		f4.reduire();
//		System.out.println("Apres reduction de f4 = " + f4);
//		//AVEC UTILISATION DE REDUIREV2
//		f4 = new Fraction(30,10);
//		System.out.println("Apres reductionV2 de f4 = " + f4.reduireV2(f4));
//		System.out.println("f4 = " + f4.toString());
//		//ECRITURE NON FRACTIONNELLE (MATH)
//		System.out.println("Ecriture non fractionnelle de f4 = " + f4.FMath(f4));
//		
//		//ORDRE
//		Fraction f5 = new Fraction(10/2);
//		System.out.println("f5 = " + f5.toString());
//		Fraction f6 = new Fraction(10/3);
//		System.out.println("f6 = " + f6.toString());
//		System.out.println("f5 > f6 ? " + f5.FSup(f6));
//	}
}
