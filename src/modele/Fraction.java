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
	//copie d'une fraction
	public Fraction(Fraction parFrac) {
		numerateur = parFrac.getNumerateur();
		denominateur = parFrac.getDenominateur();
		reduire();
	}
	/*//change un string en fraction : 3/5 devient Fraction(3,5)
	public Fraction(String parFrac) {
		int rencontre = 0;
		String numerateurString = "";
		String denominateurString = "";
		for(char ch : parFrac.toCharArray()) {
			if(ch == '/') {
				rencontre = 1;
			}
			else if(ch != '/' && rencontre == 0) {
				numerateurString += ch;
			}
			else {
				denominateurString += ch;
			}
		}
		numerateur = Integer.parseInt(numerateurString);
		if (rencontre == 0)
			denominateur = 1;
		else
			denominateur = Integer.parseInt(denominateurString);
		
		reduire();
	}*/
	//change un string en fraction : 3/5 devient Fraction(3,5)
	public Fraction(String parFrac) {
		int slash = 0;  //si il y a un slash dans le String
		int rencontre = 0;	//savoir quand on a passé le slash
		String numerateurString = "";
		String denominateurString = "";
		for(char ch : parFrac.toCharArray()) { //Test si il y a un slash dans le String
			if(ch == '/') {
				slash = 1;
			}
		}
		
		if(slash == 1) {
			for(char ch : parFrac.toCharArray()) {
				if(ch == '/') {
					rencontre = 1;
				}
				else if(ch != '/' && rencontre == 0) {
					if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
						throw new RuntimeException("lettre dans le numerateur");
					}
					else {
						numerateurString += ch;
					}
					
				}
				else {
					if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
						throw new RuntimeException("lettre dans le denominateur");
					}
					else {
						denominateurString += ch;
					}
				}
			}
			if(numerateurString == "" || denominateurString == "") {
				throw new RuntimeException("numerateur ou dénominateur vide");
			}
			if(Integer.parseInt(denominateurString) == 0) {
				throw new RuntimeException("Division par zero");
			}
			numerateur = Integer.parseInt(numerateurString);
			denominateur = Integer.parseInt(denominateurString);
		}
		else {
			for(char ch : parFrac.toCharArray()) {
				if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
					throw new RuntimeException("lettre dans le numerateur");
				}
				else {
					numerateurString += ch;
				}
			}
			if(numerateurString == "") {
				throw new RuntimeException("numerateur vide");
			}
			numerateur = Integer.parseInt(numerateurString);
			denominateur = 1;
		}

	public Fraction(Fraction parFac) {
		numerateur = parFac.getNumerateur();
		denominateur = parFac.getDenominateur();
		reduire();
	}
	
	//ISFRACTION
	// String.isFraction() dit si la string est eun fraction
	public static boolean isFraction(String parFrac) {
		int slash = 0;  //si il y a un slash dans le String
		int rencontre = 0;	//savoir quand on a passé le slash
		String numerateurString = "";
		String denominateurString = "";
		for(char ch : parFrac.toCharArray()) { //Test si il y a un slash dans le String
			if(ch == '/') {
				slash = 1;
			}
		}
		
		if(slash == 1) {
			for(char ch : parFrac.toCharArray()) {
				if(ch == '/') {
					rencontre = 1;
				}
				else if(ch != '/' && rencontre == 0) {
					if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
						return false;
					}
					else {
						numerateurString += ch;
					}
					
				}
				else {
					if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
						return false;
					}
					else {
						denominateurString += ch;
					}
				}
			}
			if(numerateurString == "" || denominateurString == "") {
				return false;
			}
			if(Integer.parseInt(denominateurString) == 0) {
				return false;
			}
		}
		else {
			for(char ch : parFrac.toCharArray()) {
				if(ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9') {
					return false;
				}
				else {
					numerateurString += ch;
				}
			}
			if(numerateurString == "") {
				return false;
			}
		}
		return true;
	}
	
	//GETTER
	public int getNumerateur() {
		return numerateur;
	}
	
	public int getDenominateur() {
		return denominateur;
	}
	
	//SETTER (ne pas oublier de recreer la fraction apres pour qu'elle soit réduite)
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
	public void reduire() {
		if(denominateur < 0) { //pour mettre le - devant
			numerateur *= -1;
			denominateur *= -1;
		}
		int pgcd = Math.abs(CalculPGCD(numerateur,denominateur));
		this.setNumerateur(numerateur/pgcd);
		this.setDenominateur(denominateur/pgcd);
	}
	
	//REDUIREV2 reduit juste pour l'affichage
	//reduit la fraction jusqu'a ce qu'elle soit irreductible -- ne sert a rien si "reduire()" est activé dans les constructeur "Fraction"
	public Fraction reduireV2(Fraction frac) {
		int pgcd = CalculPGCD(frac.getNumerateur(),frac.getDenominateur());
		frac.setNumerateur(frac.getNumerateur()/pgcd);
		frac.setDenominateur(frac.getDenominateur()/pgcd);
		return new Fraction(frac.getNumerateur(), frac.getDenominateur());
	}
	
	//ECRITURE NON FRACTIONNELLE (voir si utilise float ou double)
	//ecrit une fraction sous le format a virgule (float : 7 chiffres apres la virgule || double = 15 chiffres apres la virgule)
	public double FMath() {
		double nume = this.getNumerateur();
		double deno = this.getDenominateur();
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
		if(this.FMath() > f.FMath()) {
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
		int t1 = String.valueOf(numerateur).length();
		int t2 = String.valueOf(denominateur).length();
		String tirets = "-";
		String espaces = " ";
		if((t1 >= 2) && (t2 >= 2)) {
			if(t1 == t2) {
				for(int i = 0 ; i < t2-1 ; i++) {
					tirets = tirets.concat("-");
				}
				return numerateur + "\n" + tirets + "\n" + denominateur;
			}
			else if(t1 < t2) {
				int espace = (t2/t1)-2;
				for(int i = 0 ; i < espace ; i++) {
					espaces = espaces.concat(" ");
				}
				for(int i = 0 ; i < t2-1 ; i++) {
					tirets = tirets.concat("-");
				}
				return espaces + numerateur + "\n" + tirets + "\n" + denominateur;
			}
			else {
				int espace = (t1/t2)-2;
				for(int i = 0 ; i < espace ; i++) {
					espaces = espaces.concat(" ");
				}
				for(int i = 0 ; i <  t1-1 ; i++) {
					tirets = tirets.concat("-");
				}
				return numerateur + "\n" + tirets + "\n" + espaces + denominateur;
			}
		}
		else {
			espaces = "";
			if(t1 < t2) {
				int espace = (t2/t1)-2;
				for(int i = 0 ; i < espace ; i++) {
					espaces = espaces.concat(" ");
				}
				for(int i = 0 ; i < t2-1 ; i++) {
					tirets = tirets.concat("-");
				}
				return espaces + numerateur + "\n" + tirets + "\n" + denominateur;
			}
			else {
				int espace = (t1/t2)-2;
				for(int i = 0 ; i < espace ; i++) {
					espaces = espaces.concat(" ");
				}
				for(int i = 0 ; i <  t1-1 ; i++) {
					tirets = tirets.concat("-");
				}
				return numerateur + "\n" + tirets + "\n" + espaces + denominateur;
			}
		}
		return numerateur + "/" + denominateur;
	}
}
