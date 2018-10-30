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
	// GETTER VALUE CASE
	public Fraction getValueC(int ligne, int col) {
		return value[ligne][col];
	}
	// SETTER VALUE (1 CASE)
	public void setValue(Fraction pValue, int ligne, int col) {
		value[ligne][col] = pValue;
	}
	// SETTER VALUE (CASES D'UNE LIGNE)
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
	// Diagonale = 1
	// Le reste  = 0
	// Renvoit true (si est une matrice identité) ET false(si n'est pas une matrice identité)
	public boolean isIdentity() {
		int diago = 0; // pour j
		for(int i=0; i<getTaille(); i++) {
			for(int j=0; j<getTaille(); j++) {
				if(j==diago && (this.getValueC(i,j).getNumerateur()!=1 || this.getValueC(i,j).getDenominateur()!=1)) {
						return false;
				}
				else if(j!=diago && (this.getValueC(i,j).getNumerateur()!=0 || this.getValueC(i,j).getDenominateur()!=1)) {
						return false;
				}
			}
			diago+=1;
		}
		return true;
	}
	
	/****************************************/
	/*				CALCUL					*/
	/****************************************/
	// MODIFYLINE -> ( L3 -> 2L3 )
	// Multiplication d'une ligne par un nombre non nul.
	// Prend en paramètre : 
	// int ligne(0 ou 1 ou 2), String operand ( add(+) | substract(-) | multiply(*) | divide(/) ), Fraction pFraction
	public void modifyLine(int ligne, String operand, Fraction pFraction) {
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
	
	// MODIFYLINE 2 -> ( L3 -> L3 - 2L1 )
	// Transformation d'une ligne à l'aide d'une autre ligne
	/*	
	 * EXEMPLE pour : L3 -> L3 - 2L1
	 * 				  Ligne3 = Ligne3 - 2*Ligne1
	 * matrice.modifyLine2(2, "-", 0, new Fraction(2));
	*/										
	public void modifyLine2(int ligneA, String operand, int ligneB, Fraction multiplicateur) {
		Fraction frac;
		for(int i=0; i<getTaille(); i++) {
			for(int j=0; j<getTaille(); j++) {
				if(ligneA==i) {
					if(operand == "+") {
						frac = multiplicateur.FMultiplication(getValueC(ligneB,j));
						value[i][j] = getValueC(i,j).FAddition(frac);
					}
					else if(operand == "-") {
						frac = multiplicateur.FMultiplication(getValueC(ligneB,j));
						value[i][j] = getValueC(i,j).FSoustraction(frac);
					}
					else if(operand == "*") {
						frac = multiplicateur.FMultiplication(getValueC(ligneB,j));
						value[i][j] = getValueC(i,j).FMultiplication(frac);
					}
					else if(operand == "/") {
						frac = multiplicateur.FMultiplication(getValueC(ligneB,j));
						value[i][j] = getValueC(i,j).FDivision(frac);
					}
					else {
						System.out.println("ERREUR L'OPERATION DEMANDEE N'EXISTE PAS");
					}
				}
			}
		}
	}
	
	// CHANGELINE
	// Echange de deux lignes.
	// Prend en paramètre : int ligne(0 ou 1 ou 2), int ligne2(0 ou 1 ou 2)
	public void changeLine(int ligne, int ligne2) {
		Fraction tampon[] = new Fraction[getTaille()];
		for(int i=0; i<getTaille(); i++) {
			tampon[i] = value[ligne][i];			// Tampon = Ligne 1
		}
		for(int j=0; j<getTaille(); j++) {
			setValue(value[ligne2][j], ligne, j);	// Ligne 1 = Ligne 2	
		}
		for(int k=0; k<getTaille(); k++) {
			setValue(tampon[k], ligne2, k);			// Ligne 2 = Tampon	
		}
	}
	
			
	/****************************************/
	/*				MAIN					*/
	/****************************************/
	public static void main(String []args) {
		Fraction l_un[] = {new Fraction(10),new Fraction(1),new Fraction(12),new Fraction(34),new Fraction(71),new Fraction(7),new Fraction(65),new Fraction(99),new Fraction(2)}; 
		Fraction l_ident[] = {new Fraction(1),new Fraction(0),new Fraction(0),new Fraction(0),new Fraction(1),new Fraction(0),new Fraction(0),new Fraction(0),new Fraction(1)};
		
		Fraction fUn = new Fraction(12);
		Fraction fDeux = new Fraction(1);
		
		// MATRICE 1
//		Matrice mUn = new Matrice(3, l_un);
//		mUn.toString();
//		if(mUn.isIdentity())
//			System.out.println("EST une matrice identite");
//		else
//			System.out.println("N'EST PAS une matrice identite!");
		
		// MATRICE 2 (identité)
		Matrice ident = new Matrice(3, l_ident);
		ident.toString();
		if(ident.isIdentity())
			System.out.println("EST une matrice identite");
		else
			System.out.println("N'EST PAS une matrice identite!");

		
		// CALCUL 

		// Change une ligne
		/* NE FONCTIONNE PAS SI ON A UNE FRACTION A CAUSE DES INT MIS
		   METTRE DES FRACTIONS DèS LES PARAM */
		ident.setValue(new Fraction(5),new Fraction(1,2),new Fraction(10),1);
		ident.toString();

		// Test modif toute une ligne
		System.out.println("ADDITION +2");
		ident.modifyLine(1, "+", new Fraction(2));
		ident.toString();
		System.out.println("SOUSTRACTION -2");
		ident.modifyLine(1, "-", new Fraction(2));
		ident.toString();
		System.out.println("DIVISION /2");
		ident.modifyLine(1, "/", new Fraction(2));
		ident.toString();
		System.out.println("MULTIPLICATION *2/3");
		ident.modifyLine(1, "*", new Fraction(2,3));
		ident.toString();
		
		// Inverse 2 lignes
		System.out.println("INVERSION LIGNE 1 et 2");
		ident.changeLine(0, 1);
		ident.toString();
		
		// Modifier avec d'autre lignes
		// L3 -> L3 - 2L1
		System.out.println("L3 -> L3 - 2L1");
		ident.modifyLine2(2, "-", 0, new Fraction(2));
		ident.toString();
		// L3 -> L3 + L2
		// L3 -> L3/4
		
		
	}// MAIN
	
	

}//class Matrice