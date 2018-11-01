package modele;

public class Matrice {
	
	private int Lig;
	private int Col;
	private Fraction[][] Case;
	
															/////MATRICE/////
	
	//creer une matrice carre vide de taille parTaille
	public Matrice (int parTaille) {
		Lig = parTaille;
		Col = parTaille;
		Case = new Fraction[Lig][Col];
		this.remplir(0);
	}
	
	//creer une matrice vide de taille parLig et parCol
	public Matrice (int parLig, int parCol) {
		Lig = parLig;
		Col = parCol;
		Case = new Fraction[Lig][Col];
		this.remplir(0);
	}
	
	//creer une matrice avec un tableau donne en parametre
	public Matrice (Fraction[][] parMat) {
		Lig = parMat.length;
		Col = parMat.length;
		this.Case = new Fraction[Lig][Col];
		for(int i=0; i<this.getLig() ; i++) {
			for(int j=0; j<this.getCol() ;j++) {
				this.Case[i][j] = parMat[i][j];
			}
		}
	}
	
	//creer une copie d'une matrice
	public Matrice(Matrice A) {
		this(A.Case);
	}
	
	//REMPLIR
	//remplie une matrice de x ( remplie de 0 a sa creation pour pas que la matrice soit remplit de null et donne erreur )
	public void remplir(int x) {
		for(int i=0; i<this.getLig() ; i++) {
			for(int j=0; j<this.getCol() ;j++) {
				this.Case[i][j] = new Fraction(x);
			}
		}
	}
	
															/////GETTER/////
	//get une case
	public Fraction getCase(int i, int j) {
		if((i > this.getLig() ) || (i < 0 ) || (j > this.getCol() ) || (j < 0 )) {
			throw new RuntimeException("Case non existante");
		}
		else {
			return this.Case[i][j];
		}
	}
	//get une ligne sous forme de tableau de fraction
	public Fraction[] getLigne(int x) {
		if((x > this.getCol()-1) || (x < 0)) {
			throw new RuntimeException("Ligne non existante");
		}
		else {
			return Case[x];
		}
	}
	//get toute la matrice sous forme de tableau de fraction ( ex : {{1,2,3} , {4,5,6} , {7,8,9}} )
	public Fraction[][] getAll() {
		return Case;
	}
	//get taille matrice carre
	public int getTaille() {
		if(this.getLig() != this.getCol()) {
			throw new RuntimeException("Pas matrice carre");
		}
		else {
			return this.getLig();
		}
	}
	//get nombre ligne d'une matrice
	public int getLig() {
		return Lig;
	}
	//get nombre de colonne d'une matrice
	public int getCol() {
		return Col;
	}
	
															/////SETTER/////
	//set une case
	public void setCase(int i, int j, Fraction var) { //var int a changer en fraction
		if((i > this.getLig() ) || (i < 0 ) || (j > this.getCol() ) || (j < 0 )) {
			throw new RuntimeException("Case non existante");
		}
		else {
			this.Case[i][j] = var;
		}
	}
	//set une ligne de la matrice appelante avec un tableau donne en parametre
	public void setLigne(int x, Fraction tab[]) {
		if((x > this.getCol()-1) || (x < 0)) {
			throw new RuntimeException("Ligne non existante");
		}
		else {
			this.Case[x] = tab;
		}
	}
	
															/////FONCTION/////
	
	//renvoie une matrice identite de taille "Taille"
	public static Matrice Identite(int Taille) {
        Matrice Ident = new Matrice(Taille);
        for (int i=0; i < Taille; i++) {
        	Ident.Case[i][i] = new Fraction(1,1);
        }
        return Ident;
    }
	
	//echange deux lignes entre elles ( M[Li] <- M[Lj]   && M[Lj] <- M[Li])
	public void Echange(int i, int j) {
		Fraction[] tampon = getLigne(i);
        Case[i] = getLigne(j);
        Case[j] = tampon;
    }
	
	//COMPARE
	//compare deux matrices entre elles et renvoie true si elles sont identique, sinon false
	public boolean MCompare(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.getLig() != parMat.getLig() || Mat.getCol() != parMat.getCol()) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		for (int i=0; i < this.getLig() ;i++) {
			for(int j=0; j < this.getCol() ;j++) {
				if(!(Mat.Case[i][j].FCompare(parMat.Case[i][j]))) {
					return false;
				}
			}
		}
		return true;
	}
	
	//ISIDENTITY
	//return true si la matrice est une matrice identite, sinon false
	public boolean isIdentite() {
		int zero = 0;
		for(int i=0; i < this.getLig(); i++) {
			for(int j=0; j < this.getCol(); j++) {
				if(j==zero && !(Case[i][j].FCompare(new Fraction(1)))) {
					return false;
				}
				else if(j!=zero && !(Case[i][j].FCompare(new Fraction(0)))) {
					return false;
				}
			}
			zero +=1;
		}
		return true;
	}
	
															/////OPERATION/////
	
	//ADDITION
	//additionne deux matrices entre elles ( res <- M1 + M2 )
	public Matrice MAddition(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.getLig() != parMat.getLig() || Mat.getCol() != parMat.getCol()) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		Matrice res = new Matrice(Mat.getLig(),Mat.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ;j++) {
				res.Case[i][j] = Mat.Case[i][j].FAddition(parMat.Case[i][j]);
			}
		}
		return res;
	}
	//additionne une matrice avec une fraction ( res <- M + frac )
	public Matrice IAddition(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(Mat.getLig(),Mat.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ;j++) {
				res.Case[i][j] = Mat.Case[i][j].FAddition(parFrac);
			}
		}
		return res;
	}
	//additionne deux lignes d'une meme matrice, le resultat vas dans la premiere ligne donnee (M[Li] <- M[Li] + M[Lj])
	public Matrice LAddition(int i, int j) {
		Matrice Mat = this;
		Fraction tabi[] = Mat.getLigne(i);
		Fraction tabj[] = Mat.getLigne(j);
		for(int x=0; x < Mat.getCol(); x++) {
			tabi[x] = tabi[x].FAddition(tabj[x]);
		}
		Mat.setLigne(i,tabi);
		return Mat;
	}
	
	//SOUSTRACTION
	//soustrait deux matrices entre elles ( res <- M1 - M2 )
	public Matrice MSoustraction(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.getLig() != parMat.getLig() || Mat.getCol() != parMat.getCol()) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		Matrice res = new Matrice(Mat.getLig(),Mat.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol();j++) {
				res.Case[i][j] = Mat.Case[i][j].FSoustraction(parMat.Case[i][j]);
			}
		}
		return res;
	}
	//soustrait une matrice avec une fraction ( res <- M - frac )
	public Matrice ISoustraction(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(Mat.getLig(),Mat.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ;j++) {
				res.Case[i][j] = Mat.Case[i][j].FSoustraction(parFrac);
			}
		}
		return res;
	}
	//soustrait deux lignes d'une meme matrice, le resultat vas dans la premiere ligne donnee (M[Li] <- M[Li] - M[Lj])
		public Matrice LSoustraction(int i, int j) {
			Matrice Mat = this;
			Fraction tabi[] = Mat.getLigne(i);
			Fraction tabj[] = Mat.getLigne(j);
			for(int x=0; x < Mat.getCol(); x++) {
				tabi[x] = tabi[x].FSoustraction(tabj[x]);
			}
			Mat.setLigne(i,tabi);
			return Mat;
		}
	
	//MULTIPLICATION
	//multiplie deux matrices entre elles ( res <- M1 * M2 )
	public Matrice MMultiplication(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.getCol() != parMat.getLig()) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		Matrice res = new Matrice(Mat.getLig(),parMat.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ; j++) {
				for(int k=0; k < Mat.getCol() ; k++) {
					res.Case[i][j] = (res.getCase(i, j)).FAddition((Mat.getCase(i,k).FMultiplication(parMat.getCase(k,j))));
				}
			}
		}
		return res;
	}
	//multiplie une matrice avec une fraction ( res <- M * frac )
	public Matrice IMultiplication(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(this.getLig(),this.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ; j++) {
				res.Case[i][j] = Mat.getCase(i,j).FMultiplication(parFrac);
			}
		}
		return res;
	}
	//multiplie deux lignes d'une meme matrice, le resultat vas dans la premiere ligne donnee (M[Li] <- M[Li] * M[Lj])
		public Matrice LMultiplication(int i, int j) {
			Matrice Mat = this;
			Fraction tabi[] = Mat.getLigne(i);
			Fraction tabj[] = Mat.getLigne(j);
			for(int x=0; x < Mat.getCol(); x++) {
				tabi[x] = tabi[x].FMultiplication(tabj[x]);
			}
			Mat.setLigne(i,tabi);
			return Mat;
		}
	
	//pour DIVISION de deux matrice il faut calculer la matrice transpose, la comatrice, le determinant et sous matrice
	//ces quatres choses permettent d'avoir la matrice inverse. Puis M1/M2 = M1*(M2)^-1  (M1 multiplier par l'inverse de M2)
	//les divisions se feront donc avec FDivision
	//Impossible de faire ces quatres prerequis car il faut le faire le modulo d'une fraction
	//Donc pas de "public Matrice MDivision(Matrice parMat) ( res <- M1 / M2 ) "
	
	//divise une matrice avec une fraction ( res <- M / frac )
	public Matrice IDivision(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(Mat.getLig(),Mat.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ;j++) {
				res.Case[i][j] = Mat.Case[i][j].FDivision(parFrac);
			}
		}
		return res;
	}
	//additionne deux lignes d'une meme matrice, le resultat vas dans la premiere ligne donnee (M[Li] <- M[Li] / M[Lj])
	public Matrice LDivision(int i, int j) {
		Matrice Mat = this;
		Fraction tabi[] = Mat.getLigne(i);
		Fraction tabj[] = Mat.getLigne(j);
		for(int x=0; x < Mat.getCol(); x++) {
			tabi[x] = tabi[x].FDivision(tabj[x]);
		}
		Mat.setLigne(i,tabi);
		return Mat;
	}
	
															/////MODIFYLINE/////
	
	// MODIFYLINE -> ( L3 -> 2L3 )
	// Multiplication d'une ligne par un nombre non nul.
	// Prend en paramètre : 
	// int ligne(0 ou 1 ou 2), String operand ( add(+) | substract(-) | multiply(*) | divide(/) ), Fraction pFraction
	public void modifyLine(int ligne, String operand, Fraction pFraction) {
		for(int i=0; i<getTaille(); i++) {
			for(int j=0; j<getTaille(); j++) {
				if(ligne==i) {
					if(operand == "+") {
						Case[i][j] = Case[i][j].FAddition(pFraction);
					}
					else if(operand == "-") {
						Case[i][j] = Case[i][j].FSoustraction(pFraction);
					}
					else if(operand == "*") {
						Case[i][j] = Case[i][j].FMultiplication(pFraction);
					}
					else if(operand == "/") {
						Case[i][j] = Case[i][j].FDivision(pFraction);
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
						frac = multiplicateur.FMultiplication(getCase(ligneB,j));
						Case[i][j] = getCase(i,j).FAddition(frac);
					}
					else if(operand == "-") {
						frac = multiplicateur.FMultiplication(getCase(ligneB,j));
						Case[i][j] = getCase(i,j).FSoustraction(frac);
					}
					else if(operand == "*") {
						frac = multiplicateur.FMultiplication(getCase(ligneB,j));
						Case[i][j] = getCase(i,j).FMultiplication(frac);
					}
					else if(operand == "/") {
						frac = multiplicateur.FMultiplication(getCase(ligneB,j));
						Case[i][j] = getCase(i,j).FDivision(frac);
					}
					else {
						System.out.println("ERREUR L'OPERATION DEMANDEE N'EXISTE PAS");
					}
				}
			}
		}
	}
															/////AFFICHE/////
	//affiche une matrice
	public void Affiche() {
		for (int i=0; i < this.getLig(); i++) {
			for(int j=0; j < this.getCol();j++) {
				System.out.print(Case[i][j].toString() + " | ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		//MATRICE
		System.out.println();
		System.out.println("Matrices utilisées dans la suite du programme :");
		System.out.println();
		System.out.println("A est une matrice 3 par 3 , vide");
		Matrice A = new Matrice(3,3);
		A.Affiche();
		System.out.println();
		System.out.println("B est une matrice 3 par 3 , remplite de fractions et de nombres entiers");
		Fraction[][] tab = {{new Fraction(1,2),new Fraction(2,2),new Fraction(3,2)},{new Fraction(4,7),new Fraction(5,5),new Fraction(6,2)},{new Fraction(7,3),new Fraction(8,4),new Fraction(9,16)}};
		Matrice B = new Matrice(tab);
		B.Affiche();
		System.out.println();
		System.out.println("C est la copie de la matrice B");
		Matrice C = new Matrice(B);
		C.Affiche();
		System.out.println();
		System.out.println("D est matrice identité de taille 4");
		Matrice D = Matrice.Identite(4);
		D.Affiche();
		System.out.println();
		System.out.println("F est une matrice 3 par 3 , remplite de nombres entiers qui se suivent");
		Fraction[][] tab2 = {{new Fraction(1),new Fraction(2),new Fraction(3)},{new Fraction(4),new Fraction(5),new Fraction(6)},{new Fraction(7),new Fraction(8),new Fraction(9)}};
		Matrice F = new Matrice(tab2);
		F.Affiche();
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//COMPARE
		System.out.println("Regardons si deux matrices sont identiques ( 'true' si oui, 'false' sinon ) :");
		System.out.println("La matrice B et C sont-elles identiques ? " + B.MCompare(C));
		System.out.println("La matrice B et A sont-elles identiques ? " + B.MCompare(A));
		System.out.println();
		
		System.out.println("Regardons si une matrice est-elles bien une matrice identité ( 'true' si oui, 'false' sinon ) :");
		System.out.println("La matrice B est-elle une matrice identité ? " + B.isIdentite());
		System.out.println("La matrice D est-elle une matrice identité ? " + D.isIdentite());
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//OPERATION
		System.out.println("Quelques opérations sur la matrice F :");
		
		System.out.println("F*2 :");
		F.IMultiplication(new Fraction(2)).Affiche();
		System.out.println();
		System.out.println("Nous pouvons faire le même principe avec + , - et /");
		
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		System.out.println("PREMICE : les indices dans une matrice de taille 3 sont : 0,1,2 pour les lignes et les colonnes");
		System.out.println();
		
		System.out.println("Quelques opérations sur une seul ligne de la matrice F :");
		Matrice G = new Matrice(F);//on dit que G c'est F, mais c'est un secret
		System.out.println("Rappel de la matrice F :");
		F.Affiche();
		System.out.println("Nous allons additionner la ligne 1 à la ligne 0 de la matrice F, cela donne :");
		F.LAddition(0,1);
		F.Affiche();
		System.out.println();
		System.out.println("Nous pouvons faire le même principe avec - , * et /");
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//ECHANGE
		System.out.println("Nous allons Echanger la ligne 0 et la ligne 2 de la matrice F, voici ce que l'on obtient :");
		G.Echange(0,2);
		G.Affiche();
		G.Echange(0,2); //pour remettre comme avant, pour la suite
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//GETTER SETTER
		
		System.out.println("Nous allons récupérer la taille de la matrice F :");
		System.out.println("Rappel de la matrice F :");
		G.Affiche();
		System.out.println("La taille de la matrice F est : " + G.getTaille());
		System.out.println();
		
		System.out.println("Nous allons récupérer une case en particulier de la matrice F :");
		System.out.println("La case [1,2] de la matrice F est : " + G.getCase(1,2));
		System.out.println();
		
		System.out.println("Nous allons récupérer une ligne en particulier de la matrice F :");
		System.out.println("La ligne 1 de la Matrice F est :");
		Fraction tab3[] = G.getLigne(1);
		System.out.println(tab3[0] + " | " + tab3[1] + " | " + tab3[2]);
		System.out.println();
		
		System.out.println("Nous allons récupérer la matrice F entière sous forme de tableau :");
		Fraction tab4[][] = G.getAll();
		System.out.print("[");
		for(int i=0 ; i < G.getTaille() ; i++) {
			System.out.print("[");
			for(int j=0 ; j < G.getTaille() ; j++) {
				System.out.print(tab4[i][j]);
				if(j!=2)
					System.out.print(",");
			}
			System.out.print("]");
		}
		System.out.print("]");
		System.out.println();
		System.out.println();
		
		//
		
		System.out.println("Nous allons changer une case en particulier de la matrice F :");
		System.out.println("Nous allons changer la case [1][2] de la matrice F par 10");
		G.setCase(1,2,new Fraction(10));
		System.out.println("La matrice F devient alors :");
		G.Affiche();
		System.out.println();
		
		System.out.println("Nous allons changer une ligne en particulier de la matrice F :");
		System.out.println("Nous allons changer la ligne 2 de la matrice F par ( 6,1,(3/2) )");
		Fraction[] tab5 = {new Fraction(6), new Fraction(1), new Fraction(3,2)};
		G.setLigne(2,tab5);
		System.out.println("La matrice F devient alors :");
		G.Affiche();
		System.out.println();
		System.out.println("-----------------------------");
		
		
		///////////////// Nancy /////////////////
		/*
		// Test modif toute une ligne
		Matrice Nancy = Matrice.Identite(3);
		System.out.println("Matrice Identite Nancy");
		Nancy.Affiche();
		System.out.println();
		System.out.println("ADDITION +2 sur la ligne 1 (donc la deuxième ligne)");
		Nancy.modifyLine(1, "+", new Fraction(2));
		Nancy.Affiche();
		System.out.println();
		System.out.println("SOUSTRACTION -2 sur la ligne 1");
		Nancy.modifyLine(1, "-", new Fraction(2));
		Nancy.Affiche();
		System.out.println();
		System.out.println("DIVISION /2 sur la ligne 1");
		Nancy.modifyLine(1, "/", new Fraction(2));
		Nancy.Affiche();
		System.out.println();
		System.out.println("MULTIPLICATION *2/3 sur la ligne 1");
		Nancy.modifyLine(1, "*", new Fraction(2,3));
		Nancy.Affiche();
		System.out.println();
		System.out.println();
				
		// Modifier avec d'autre lignes
		// L3 -> L3 - 2L1
		System.out.println("modifyLine2");
		System.out.println("L3 -> L3 - 2L1");
		Nancy.modifyLine2(2, "-", 0, new Fraction(2));
		Nancy.Affiche();
		// L3 -> L3 + L2
		// L3 -> L3/4
		*/
	}
}