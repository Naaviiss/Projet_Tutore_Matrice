package modele;

public class Matrice {
	public int taille;
	public Fraction value[][];
	
	// CREATE MATRICE
	// prend en paramètre - la taille de la matrice - une liste de int
	// Remplis la matrice des éléments de la liste de int
	public Matrice(int parTaille, Fraction liste[]){
		taille = parTaille;
		value = new Fraction[taille][taille];
		int element=0;
		for(int i=0; i<taille; i++) {
			for(int j=0; j<taille; j++) {
				value[i][j]=liste[element];
				element+=1;
			}
		}		
	}
	
	// GETTER TAILLE
	public int getTaille() {
		return taille;
	}
	// GETTER VALUE
	public Fraction[][] getValue() {
		return value;
	}
	// SETTER VALUE
	public void setValue(Fraction pValue, int ligne, int col) {
		value[ligne][col] = pValue;
	}
	public void setValue(Fraction value1, Fraction value2, Fraction value3, int ligne) {
		Fraction liste[] = {value1, value2, value3};
		for(int i=0; i<getTaille(); i++) {
			setValue(liste[i], ligne, i);
		}
		
	}
	
	// DISPLAY MATRICE
	// prend en paramètre la taille de la matrice
	// Affiche la matrice
	public String toString() {
		for(int i=0; i<getTaille(); i++) {
			System.out.println("-------------");
			for(int j=0; j<getTaille(); j++) {
				System.out.print("| "+ value[i][j] +" ");
			}
			System.out.println("|");
		}
		System.out.println("-------------");
		return "";
	}
	
	// MATRICE IDENTITE ?
	// Les elements de la diagonale = 1
	// Le reste = 0
	public boolean isIdentity() {
		int zero = 0; // pour j
		Fraction frac_un = new Fraction(1), frac_zero = new Fraction(0);
		for(int i=0; i<getTaille(); i++) {
			for(int j=0; j<getTaille(); j++) {
				if(j==zero && value[i][j]!=frac_un){
					return false;
				}
				else if(j!=zero && value[i][j]!=frac_zero) {
					return false;
				}
			}
			zero+=1;
		}
		return true;
	}
	
	// LIGNE + OPERATION
	// operand possible | add(+) | substract(-) | multiply(*) | divide(/)
	// param ligne entre : 0 et 2
	public void changeLine(int ligne, String operand, Fraction pFraction) {
		for(int i=0; i<getTaille(); i++) {
			for(int j=0; j<getTaille(); j++) {
				if(ligne==i) {
					if(operand == "+") {
						value[i][j] = value[i][j].FAddition(pFraction);
					}
					else if(operand == "-") {
						value[i][j] = value[i][j].FSoustraction(pFraction);
					}
					else if(operand == "*") {
						value[i][j] = value[i][j].FMultiplication(pFraction);
					}
					else if(operand == "/") {
						value[i][j] = value[i][j].FDivision(pFraction);
					}
					else {
						System.out.println("ERREUR L'OPERATION DEMANDEE N'EXISTE PAS");
					}
				}//if
			}
		}
	}
	
	//////////////////////
	// 		MAIN 		//
	//////////////////////
	public static void main(String []args) {
		Fraction l_un[] = {new Fraction(10),new Fraction(1),new Fraction(12),new Fraction(34),new Fraction(71),new Fraction(7),new Fraction(65),new Fraction(99),new Fraction(2)}; 
		//Fraction l_deux[] = {12,21,31,24,14,94,66,36,46}; 
		Fraction l_ident[] = {new Fraction(1),new Fraction(0),new Fraction(0),new Fraction(0),new Fraction(1),new Fraction(0),new Fraction(0),new Fraction(0),new Fraction(1)};
		
		//Matrice un = new Matrice(3, l_un);
		//un.toString();
		
		/*
		Matrice deux = new Matrice(3, l_deux);
		deux.toString();
		if(deux.isIdentity())
			System.out.println("C'est une matrice identite");
		else
			System.out.println("Ce n'est PAS une matrice identite");
		*/	
		
		Matrice ident = new Matrice(3, l_ident);
		ident.toString();
		if(ident.isIdentity())
			System.out.println("C'est une matrice identite");
		else
			System.out.println("Ce n'est PAS une matrice identite");
		
		// Change une ligne
		// NE FONCTIONNE PAS SI ON A UNE FRACTION A CAUSE DES INT MIS
		// METTRE DES FRACTIONS DèS LES PARAM
		ident.setValue(new Fraction(5),new Fraction(1,2),new Fraction(10),1);
		ident.toString();

		// Test modif toute une ligne
		System.out.println("ADDITION");
		ident.changeLine(1, "+", new Fraction(2));
		ident.toString();
		System.out.println("SOUSTRACTION");
		ident.changeLine(1, "-", new Fraction(2));
		ident.toString();
		System.out.println("DIVISION");
		ident.changeLine(1, "/", new Fraction(2));
		ident.toString();
		System.out.println("MULTIPLICATION");
		ident.changeLine(1, "*", new Fraction(2,3));
		ident.toString();
		
	}// MAIN
	
	

}//class Matrice