package modele;

public class Matrice {
	
	private int chLigne;
	private int chCol;
	private Fraction[][] chCase;
	
	/////MATRICE/////
	
	//creer une matrice carre vide de taille parTaille
	public Matrice (int parTaille) {
		chLigne = parTaille;
		chCol = parTaille;
		chCase = new Fraction[chLigne][chCol];
		this.remplir(0);
	}
	
	//creer une matrice vide de taille parLig et parCol
	public Matrice (int parLig, int parCol) {
		chLigne = parLig;
		chCol = parCol;
		chCase = new Fraction[chLigne][chCol];
		this.remplir(0);
	}
	
	//creer une matrice avec un tableau donne en parametre
	public Matrice (Fraction[][] parMat) {
		chLigne = parMat.length;
		chCol = parMat.length;
		this.chCase = new Fraction[chLigne][chCol];
		for(int i=0; i<this.getLig() ; i++) {
			for(int j=0; j<this.getCol() ;j++) {
				this.chCase[i][j] = parMat[i][j];
			}
		}
	}
	
	//creer une copie d'une matrice
	public Matrice(Matrice A) {
		this(A.chCase);
	}
	
	//REMPLIR
	//remplie une matrice de x ( remplie de 0 a sa creation pour pas que la matrice soit remplit de null et donne erreur )
	public void remplir(int x) {
		for(int i=0; i<this.getLig() ; i++) {
			for(int j=0; j<this.getCol() ;j++) {
				this.chCase[i][j] = new Fraction(x);
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
			return this.chCase[i][j];
		}
	}
	//get une ligne sous forme de tableau de fraction
	public Fraction[] getLigne(int x) {
		if((x > this.getCol()-1) || (x < 0)) {
			throw new RuntimeException("Ligne non existante");
		}
		else {
			return chCase[x];
		}
	}
	//get toute la matrice sous forme de tableau de fraction ( ex : {{1,2,3} , {4,5,6} , {7,8,9}} )
	public Fraction[][] getAll() {
		return chCase;
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
		return chLigne;
	}
	//get nombre de colonne d'une matrice
	public int getCol() {
		return chCol;
	}
	
															/////SETTER/////
	//set une case
	public void setCase(int i, int j, Fraction var) { //var int a changer en fraction
		if((i > this.getLig() ) || (i < 0 ) || (j > this.getCol() ) || (j < 0 )) {
			throw new RuntimeException("Case non existante");
		}
		else {
			this.chCase[i][j] = var;
		}
	}
	//set une ligne de la matrice appelante avec un tableau donne en parametre
	public void setLigne(int x, Fraction tab[]) {
		if((x > this.getCol()-1) || (x < 0)) {
			throw new RuntimeException("Ligne non existante");
		}
		else {
			this.chCase[x] = tab;
		}
	}
	
	/////METHODES/////
	
	//renvoie une matrice identite de taille "Taille"
	public static Matrice identite(int Taille) {
        Matrice Ident = new Matrice(Taille);
        for (int i=0; i < Taille; i++) {
        	Ident.chCase[i][i] = new Fraction(1,1);
        }
        return Ident;
    }
	
	//echange deux lignes entre elles ( M[Li] <- M[Lj]   && M[Lj] <- M[Li])
	public void echange(int i, int j) {
		Fraction[] tampon = getLigne(i);
        chCase[i] = getLigne(j);
        chCase[j] = tampon;
    }
	
	//COMPARE
	//compare deux matrices entre elles et renvoie true si elles sont identique, sinon false
	public boolean mCompare(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.getLig() != parMat.getLig() || Mat.getCol() != parMat.getCol()) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		for (int i=0; i < this.getLig() ;i++) {
			for(int j=0; j < this.getCol() ;j++) {
				if(!(Mat.chCase[i][j].FCompare(parMat.chCase[i][j]))) {
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
				if(j==zero && !(chCase[i][j].FCompare(new Fraction(1)))) {
					return false;
				}
				else if(j!=zero && !(chCase[i][j].FCompare(new Fraction(0)))) {
					return false;
				}
			}
			zero +=1;
		}
		return true;
	}
	
	/////OPERATIONS/////
	
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
				res.chCase[i][j] = Mat.chCase[i][j].FAddition(parMat.chCase[i][j]);
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
				res.chCase[i][j] = Mat.chCase[i][j].FAddition(parFrac);
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
				res.chCase[i][j] = Mat.chCase[i][j].FSoustraction(parMat.chCase[i][j]);
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
				res.chCase[i][j] = Mat.chCase[i][j].FSoustraction(parFrac);
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
					res.chCase[i][j] = (res.getCase(i, j)).FAddition((Mat.getCase(i,k).FMultiplication(parMat.getCase(k,j))));
				}
			}
		}
		return res;
	}
	//multiplie une matrice avec une fraction ( res <- M * frac )
	public Matrice iMultiplication(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(this.getLig(),this.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ; j++) {
				res.chCase[i][j] = Mat.getCase(i,j).FMultiplication(parFrac);
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
	public Matrice iDivision(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(Mat.getLig(),Mat.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ;j++) {
				res.chCase[i][j] = Mat.chCase[i][j].FDivision(parFrac);
			}
		}
		return res;
	}
	//additionne deux lignes d'une meme matrice, le resultat vas dans la premiere ligne donnee (M[Li] <- M[Li] / M[Lj])
	public Matrice lDivision(int i, int j) {
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
	// Prend en param�tre : 
	// int ligne(0 ou 1 ou 2),Fraction pFraction
	public void modifyLine(int ligne, Fraction pFraction) {
		for(int i=0; i<getTaille(); i++) {
			for(int j=0; j<getTaille(); j++) {
				if(ligne==i) {
					chCase[i][j] = chCase[i][j].FMultiplication(pFraction);
				}//if
			}
		}
	}
	
	// MODIFYLINE 2 -> ( L3 -> L3 - 2L1 )
	// Transformation d'une ligne � l'aide d'une autre ligne
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
					frac = multiplicateur.FMultiplication(getCase(ligneB,j));
					if(operand.equals("+")) {
						chCase[i][j] = getCase(i,j).FAddition(frac);
					}
					else if(operand.equals("-")) {
						chCase[i][j] = getCase(i,j).FSoustraction(frac);
					}
					else if(operand.equals("*")) {
						chCase[i][j] = getCase(i,j).FMultiplication(frac);
					}
					else if(operand.equals("/")) {
						chCase[i][j] = getCase(i,j).FDivision(frac);
					}
					else {
						System.out.println("ERREUR L'OPERATION DEMANDEE N'EXISTE PAS");
					}
				}
			}
		}
	}
	
	////AFFICHE/////
	//affiche une matrice
			public String toString() {
			int tailleMatrice = this.getTaille();  //taille de la matrice carr�
			int[][] TabFractionNumerateur = new int[tailleMatrice][tailleMatrice];  //tableau contenant les numerateur des fraction
			int[][] TabFractionDenominateur = new int[tailleMatrice][tailleMatrice];  //tableau contenant les denominateurs des fraction
			int[][] TailleMaxFraction = new int[tailleMatrice][tailleMatrice]; //le plus grand entre num et den
			int tailleMax = 5;
			String tirets = "";
			String espaces = "";
			String espaces2 = "";
			String resultat = "";
			
			for(int i=0; i < this.getLig(); i++) {
				for(int j=0; j < this.getCol(); j++) {
					TabFractionNumerateur[i][j] = this.getCase(i,j).getNumerateur();
					TabFractionDenominateur[i][j] = this.getCase(i,j).getDenominateur();
					TailleMaxFraction[i][j] = String.valueOf(Math.max(TabFractionNumerateur[i][j],TabFractionDenominateur[i][j])).length();
				}	
			}
			
			for(int i=0; i < tailleMatrice; i++) {
				for(int k=0; k < tailleMatrice; k++) {
					for(int j=0; j < tailleMatrice; j++) {
						if(TabFractionDenominateur[i][j] == 1) {
							if(k%tailleMatrice == 0) {
								for(int x=0; x < tailleMax; x++) {
									espaces = espaces.concat(" ");
								}
								resultat = resultat.concat(espaces);
								//System.out.print(espaces + "|");
							}
							else if(k%tailleMatrice == 1) {
								float ecartnum = (tailleMax-String.valueOf(TabFractionNumerateur[i][j]).length());
								for(int x=0; x < Math.floor(ecartnum)/2; x++) {
									espaces = espaces.concat(" ");
									espaces2 = espaces2.concat(" ");
								}
								if(String.valueOf(TabFractionNumerateur[i][j]).length() == 2) {
									espaces = espaces.substring(0,espaces.length()-1);
								}
								resultat = resultat.concat(espaces + TabFractionNumerateur[i][j] + espaces2);
								//System.out.print(espaces + TabFractionNumerateur[i][j] + espaces2 + "|");
							}
							else {
								for(int x=0; x < tailleMax; x++) {
									espaces = espaces.concat(" ");
								}
								resultat = resultat.concat(espaces);
								//System.out.print(espaces + "|");
							}
						}
						else {
							if(k%tailleMatrice == 0) {
								float ecartnum = (tailleMax-String.valueOf(TabFractionNumerateur[i][j]).length());
								for(int x=0; x < Math.floor(ecartnum)/2; x++) {
									espaces = espaces.concat(" ");
									espaces2 = espaces2.concat(" ");
								}
								if(String.valueOf(TabFractionNumerateur[i][j]).length() == 2) {
									espaces = espaces.substring(0,espaces.length()-1);
								}
								resultat = resultat.concat(espaces + TabFractionNumerateur[i][j] + espaces2);
								//System.out.print(espaces + TabFractionNumerateur[i][j] + espaces2 + "|");
							}
							else if(k%tailleMatrice == 1) {
								for(int y=0; y < TailleMaxFraction[i][j]; y++) {
									tirets = tirets.concat("-");
								}
								for(int y=0; y < Math.floor(tailleMax-TailleMaxFraction[i][j])/2; y++) {
									espaces = espaces.concat(" ");
									espaces2 = espaces2.concat(" ");
								}
								if(String.valueOf(espaces).length() == 2 && String.valueOf(tirets).length() == 2) {
									espaces = espaces.substring(0,espaces.length()-1);
								}
								resultat = resultat.concat(espaces + tirets + espaces2);
								//System.out.print(espaces + tirets + espaces2 + "|");
							}
							else {
								float ecartden = (tailleMax-String.valueOf(TabFractionDenominateur[i][j]).length());
								for(int x=0; x < Math.floor(ecartden)/2; x++) {
									espaces = espaces.concat(" ");
									espaces2 = espaces2.concat(" ");
								}
								if(String.valueOf(TabFractionDenominateur[i][j]).length() == 2) {
									espaces = espaces.substring(0,espaces.length()-1);
								}
								resultat = resultat.concat(espaces + TabFractionDenominateur[i][j] + espaces2);
								//System.out.print(espaces + TabFractionDenominateur[i][j] + espaces2 + "|");
							}
						}
						espaces = "";
						espaces2 = "";
						tirets = "";
					}
					resultat = resultat.concat("\n");
					//System.out.println();
				}
				resultat = resultat.concat("\n");
				//System.out.println();
			}
			
			//System.out.println(resultat.toString());
			return resultat;
		}
		//affiche une ligne d'une matrice
		public String toStringLigne(int parLigne) {
			Fraction[] ligne = new Fraction[this.getTaille()];
			for(int i=0; i < this.getTaille(); i++) {
				for(int j=0; j < this.getTaille(); j++) {
					if(i == parLigne) {
						ligne[j] = this.getCase(1,j);
					}
				}
			}
			
			int tailleMatrice = this.getCol();  //taille de la matrice 1 sur colonne
			int[] TabFractionNumerateur = new int[tailleMatrice];  //tableau contenant les numerateur des fraction
			int[] TabFractionDenominateur = new int[tailleMatrice];  //tableau contenant les denominateurs des fraction
			int[] TailleMaxFraction = new int[tailleMatrice]; //le plus grand entre num et den
			int tailleMax = 5;
			String tirets = "";
			String espaces = "";
			String espaces2 = "";
			String resultat = "";
			
			for(int j=0; j < this.getCol(); j++) {
				TabFractionNumerateur[j] = this.getCase(parLigne,j).getNumerateur();
				TabFractionDenominateur[j] = this.getCase(parLigne,j).getDenominateur();
				TailleMaxFraction[j] = String.valueOf(Math.max(TabFractionNumerateur[j],TabFractionDenominateur[j])).length();
			}
			
			for(int k=0; k < tailleMatrice; k++) {
				for(int j=0; j < tailleMatrice; j++) {
					if(TabFractionDenominateur[j] == 1) {
						if(k%tailleMatrice == 0) {
							for(int x=0; x < tailleMax; x++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces);
							//System.out.print(espaces + "|");
						}
						else if(k%tailleMatrice == 1) {
							float ecartnum = (tailleMax-String.valueOf(TabFractionNumerateur[j]).length());
							for(int x=0; x < Math.floor(ecartnum)/2; x++) {
								espaces = espaces.concat(" ");
								espaces2 = espaces2.concat(" ");
							}
							if(String.valueOf(TabFractionNumerateur[j]).length() == 2) {
								espaces = espaces.substring(0,espaces.length()-1);
							}
							resultat = resultat.concat(espaces + TabFractionNumerateur[j] + espaces2);
							//System.out.print(espaces + TabFractionNumerateur[j] + espaces2 + "|");
						}
						else {
							for(int x=0; x < tailleMax; x++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces);
							//System.out.print(espaces + "|");
						}
					}
					else {
						if(k%tailleMatrice == 0) {
							float ecartnum = (tailleMax-String.valueOf(TabFractionNumerateur[j]).length());
							for(int x=0; x < Math.floor(ecartnum)/2; x++) {
								espaces = espaces.concat(" ");
								espaces2 = espaces2.concat(" ");
							}
							if(String.valueOf(TabFractionNumerateur[j]).length() == 2) {
								espaces = espaces.substring(0,espaces.length()-1);
							}
							resultat = resultat.concat(espaces + TabFractionNumerateur[j] + espaces2);
							//System.out.print(espaces + TabFractionNumerateur[j] + espaces2 + "|");
						}
						else if(k%tailleMatrice == 1) {
							for(int y=0; y < TailleMaxFraction[j]; y++) {
								tirets = tirets.concat("-");
							}
							for(int y=0; y < Math.floor(tailleMax-TailleMaxFraction[j])/2; y++) {
								espaces = espaces.concat(" ");
								espaces2 = espaces2.concat(" ");
							}
							if(String.valueOf(espaces).length() == 2 && String.valueOf(tirets).length() == 2) {
								espaces = espaces.substring(0,espaces.length()-1);
							}
							resultat = resultat.concat(espaces + tirets + espaces2);
							//System.out.print(espaces + tirets + espaces2 + "|");
						}
						else {
							float ecartden = (tailleMax-String.valueOf(TabFractionDenominateur[j]).length());
							for(int x=0; x < Math.floor(ecartden)/2; x++) {
								espaces = espaces.concat(" ");
								espaces2 = espaces2.concat(" ");
							}
							if(String.valueOf(TabFractionDenominateur[j]).length() == 2) {
								espaces = espaces.substring(0,espaces.length()-1);
							}
							resultat = resultat.concat(espaces + TabFractionDenominateur[j] + espaces2);
							//System.out.print(espaces + TabFractionDenominateur[j] + espaces2 + "|");
						}
					}
					espaces = "";
					espaces2 = "";
					tirets = "";
				}
				resultat = resultat.concat("\n");
				//System.out.println();
			}
			resultat = resultat.concat("\n");
			//System.out.println();
			//System.out.println(resultat.toString());
			return resultat;
		}
}