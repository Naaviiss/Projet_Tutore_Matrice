package Modele;

/**
 * Matrice est une classe qui permet la gestion de tout ce qui concerne les
 * matrices
 */
public class Matrice {

	/**
	 * Le nombre de lignes de la matrice
	 */
	private int chLigne;

	/**
	 * Le nombre de colonnes de la matrice
	 */
	private int chCol;

	/**
	 * Le tableau correspondant à toutes les cases de la matrice
	 */
	private Fraction[][] chCase;

	/**
	 * Constructeur par défaut de la classe Matrice
	 * 
	 * @param parTaille
	 *            la taille de la matrice
	 */
	public Matrice(int parTaille) {
		chLigne = parTaille;
		chCol = parTaille;
		chCase = new Fraction[chLigne][chCol];
		this.remplir(0);
	}

	/**
	 * Autre constructeur par défaut de la classe Matrice
	 * 
	 * @param parLig
	 *            le nombre de lignes de la matrice
	 * @param parCol
	 *            le nombre de colonnes de la matrice
	 */
	public Matrice(int parLig, int parCol) {
		chLigne = parLig;
		chCol = parCol;
		chCase = new Fraction[chLigne][chCol];
		this.remplir(0);
	}

	/**
	 * Autre constructeur par défaut de la classe Matrice
	 * 
	 * @param parMat
	 *            les valeurs de chaque case
	 */
	public Matrice(Fraction[][] parMat) {
		chLigne = parMat.length;
		chCol = parMat.length;
		this.chCase = new Fraction[chLigne][chCol];
		for (int i = 0; i < this.getLig(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.chCase[i][j] = parMat[i][j];
			}
		}
	}

	/**
	 * Créer une copie d'une matrice
	 * 
	 * @param A
	 *            une matrice
	 */
	public void copie(Matrice A) {
		for (int i = 0; i < chLigne; i++) {
			for (int j = 0; j < chCol; j++) {
				this.chCase[i][j] = A.getCase(i, j);
			}
		}
	}

	/**
	 * Remplie une matrice d'un certain élément.
	 * 
	 * @param x
	 *            un élément en question
	 */
	public void remplir(int x) {
		for (int i = 0; i < this.getLig(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				this.chCase[i][j] = new Fraction(x);
			}
		}
	}

	/**
	 * Rend la valeur d'une case de la matrice voulue
	 * 
	 * @param i
	 *            la ligne de la case
	 * @param j
	 *            la colonne de la case
	 * @return la valeur de la case de la matrice voulue
	 */
	public Fraction getCase(int i, int j) {
		if ((i > this.getLig()) || (i < 0) || (j > this.getCol()) || (j < 0))
			throw new RuntimeException("Case non existante");
		else
			return this.chCase[i][j];
	}

	/**
	 * Renvoie une ligne de la matrice sous forme de tableau de fraction
	 * 
	 * @param x
	 *            l'indice de la ligne
	 * @return une lignde ed la matrice sous forme de tableau de fraction
	 */
	public Fraction[] getLigne(int x) {
		if ((x > this.getCol() - 1) || (x < 0))
			throw new RuntimeException("Ligne non existante");
		else
			return chCase[x];
	}

	/**
	 * Renvoie la matrice sous forme de tableau de fraction
	 * 
	 * @return la matrice sous forme de tableau de fraction
	 */
	public Fraction[][] getAll() {
		return chCase;
	}

	/**
	 * Renvoie la taille de la matrice
	 * 
	 * @return la taille de la matrice
	 */
	public int getTaille() {
		if (this.getLig() != this.getCol())
			throw new RuntimeException("Pas matrice carre");
		else
			return this.getLig();
	}

	/**
	 * Renvoie le nombre de lignes d'une matrice
	 * 
	 * @return le nombre de lignes d'une matrice
	 */
	public int getLig() {
		return chLigne;
	}

	/**
	 * Renvoie le nombre de colonnes d'une matrice
	 * 
	 * @return le nombre de colonnes d'une matrice
	 */
	public int getCol() {
		return chCol;
	}

	/**
	 * Change la valeur d'une case de la matrice
	 * 
	 * @param i
	 *            la ligne de la case
	 * @param j
	 *            la colonne de la case
	 * @param var
	 *            la nouvelle valeur de la case
	 */
	public void setCase(int i, int j, Fraction var) {
		if ((i > this.getLig()) || (i < 0) || (j > this.getCol()) || (j < 0))
			throw new RuntimeException("Case non existante");
		else
			this.chCase[i][j] = var;
	}

	/**
	 * Change la valeur d'une ligne entière de la fraction
	 * 
	 * @param x
	 *            l'indice de la ligne
	 * @param tab
	 *            les nouvelles valeurs de la ligne
	 */
	public void setLigne(int x, Fraction tab[]) {
		if ((x > this.getCol() - 1) || (x < 0)) {
			throw new RuntimeException("Ligne non existante");
		} else {
			this.chCase[x] = tab;
		}
	}

	/**
	 * Renvoie une matrice identitée de la matrice
	 * 
	 * @param Taille
	 *            une taille
	 * @return une matrice identitée
	 */
	public static Matrice identite(int Taille) {
		Matrice Ident = new Matrice(Taille);
		for (int i = 0; i < Taille; i++) {
			Ident.chCase[i][i] = new Fraction(1, 1);
		}
		return Ident;
	}

	/**
	 * Echange deux lignes entre elles ( M[Li] <- M[Lj] && M[Lj] <- M[Li])
	 * 
	 * @param i
	 *            l'indice de la ligne
	 * @param j
	 *            l'indice de la colonne
	 */
	public void echange(int i, int j) {
		Fraction[] tampon = getLigne(i);
		chCase[i] = getLigne(j);
		chCase[j] = tampon;
	}

	/**
	 * Compare deux matrices entre elles
	 * 
	 * @param parMat
	 *            une matrice
	 * @return vrai si elles sont identiques, sinon faux.
	 */
	public boolean mCompare(Matrice parMat) {
		Matrice Mat = this;
		if (Mat.getLig() != parMat.getLig() || Mat.getCol() != parMat.getCol())
			throw new RuntimeException("Erreur dimensions des deux matrices");
		for (int i = 0; i < this.getLig(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				if (!(Mat.chCase[i][j].FCompare(parMat.chCase[i][j])))
					return false;
			}
		}
		return true;
	}

	/**
	 * Compare deux matrices entre elles et vérifie si elles sont identitées ou non
	 * 
	 * @return vrai si l'une est l'identiée de l'autre, sinon faux.
	 */
	public boolean isIdentite() {
		int zero = 0;
		for (int i = 0; i < this.getLig(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				if (j == zero && !(chCase[i][j].FCompare(new Fraction(1))))
					return false;

				else if (j != zero && !(chCase[i][j].FCompare(new Fraction(0))))
					return false;
			}
			zero += 1;
		}
		return true;
	}

	/**
	 * Additionne deux matrices entre elles ( res <- M1 + M2 )
	 * 
	 * @param parMat
	 *            une matrice
	 * @return l'addition des deux matrices
	 */
	public Matrice MAddition(Matrice parMat) {
		Matrice Mat = this;
		if (Mat.getLig() != parMat.getLig() || Mat.getCol() != parMat.getCol())
			throw new RuntimeException("Erreur dimensions des deux matrices");
		Matrice res = new Matrice(Mat.getLig(), Mat.getCol());
		for (int i = 0; i < res.getLig(); i++) {
			for (int j = 0; j < res.getCol(); j++) {
				res.chCase[i][j] = Mat.chCase[i][j].FAddition(parMat.chCase[i][j]);
			}
		}
		return res;
	}

	/**
	 * Additionne une matrice avec une fraction ( res <- M + frac )
	 * 
	 * @param parFrac
	 *            une fraction
	 * @return l'addition d'une matrice avec une fraction
	 */
	public Matrice IAddition(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(Mat.getLig(), Mat.getCol());
		for (int i = 0; i < res.getLig(); i++) {
			for (int j = 0; j < res.getCol(); j++) {
				res.chCase[i][j] = Mat.chCase[i][j].FAddition(parFrac);
			}
		}
		return res;
	}

	/**
	 * Additionne deux ligne d'une même matrice (M[Li] <- M[Li] + M[Lj]) Le résultat
	 * va dans la première ligne donnée
	 * 
	 * @param i
	 *            l'indice de la première ligne
	 * @param j
	 *            l'indice de la première ligne
	 * @return l'addition des deux lignes
	 */
	public Matrice LAddition(int i, int j) {
		Matrice Mat = this;
		Fraction tabi[] = Mat.getLigne(i);
		Fraction tabj[] = Mat.getLigne(j);
		for (int x = 0; x < Mat.getCol(); x++) {
			tabi[x] = tabi[x].FAddition(tabj[x]);
		}
		Mat.setLigne(i, tabi);
		return Mat;
	}

	/**
	 * Soustrait deux matrices entre elles ( res <- M1 - M2 )
	 * 
	 * @param parMat
	 *            une matrice
	 * @return la soustraction des deux matrices
	 */
	public Matrice MSoustraction(Matrice parMat) {
		Matrice Mat = this;
		if (Mat.getLig() != parMat.getLig() || Mat.getCol() != parMat.getCol())
			throw new RuntimeException("Erreur dimensions des deux matrices");
		Matrice res = new Matrice(Mat.getLig(), Mat.getCol());
		for (int i = 0; i < res.getLig(); i++) {
			for (int j = 0; j < res.getCol(); j++) {
				res.chCase[i][j] = Mat.chCase[i][j].FSoustraction(parMat.chCase[i][j]);
			}
		}
		return res;
	}

	/**
	 * Soustrait une matrice avec une fraction ( res <- M - frac )
	 * 
	 * @param parFrac
	 *            une fraction
	 * @return la soustraction d'une matrice avec un fraction
	 */
	public Matrice ISoustraction(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(Mat.getLig(), Mat.getCol());
		for (int i = 0; i < res.getLig(); i++) {
			for (int j = 0; j < res.getCol(); j++) {
				res.chCase[i][j] = Mat.chCase[i][j].FSoustraction(parFrac);
			}
		}
		return res;
	}

	/**
	 * Soustrait deux lignes d'une même matrice Le résultat va dans la première
	 * ligne donnée (M[Li] <- M[Li] - M[Lj])
	 * 
	 * @param i
	 *            la première ligne donnée
	 * @param j
	 *            la deuxième lignée donnée
	 * @return la soustraction de deux lignes d'une même matrice
	 */
	public Matrice LSoustraction(int i, int j) {
		Matrice Mat = this;
		Fraction tabi[] = Mat.getLigne(i);
		Fraction tabj[] = Mat.getLigne(j);
		for (int x = 0; x < Mat.getCol(); x++) {
			tabi[x] = tabi[x].FSoustraction(tabj[x]);
		}
		Mat.setLigne(i, tabi);
		return Mat;
	}

	/**
	 * Multiplie deux matrices entre elles ( res <- M1 * M2 )
	 * 
	 * @param parMat
	 *            une matrice
	 * @return la multiplication de deux matrices entre elles
	 */
	public Matrice MMultiplication(Matrice parMat) {
		Matrice Mat = this;
		if (Mat.getCol() != parMat.getLig())
			throw new RuntimeException("Erreur dimensions des deux matrices");
		Matrice res = new Matrice(Mat.getLig(), parMat.getCol());
		for (int i = 0; i < res.getLig(); i++) {
			for (int j = 0; j < res.getCol(); j++) {
				for (int k = 0; k < Mat.getCol(); k++) {
					res.chCase[i][j] = (res.getCase(i, j))
							.FAddition((Mat.getCase(i, k).FMultiplication(parMat.getCase(k, j))));
				}
			}
		}
		return res;
	}

	/**
	 * Multiplie une matrice avec une fraction ( res <- M * frac )
	 * 
	 * @param parFrac
	 *            une fraction
	 * @return la multiplication d'une matrice avec une fraction
	 */
	public Matrice iMultiplication(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(this.getLig(), this.getCol());
		for (int i = 0; i < res.getLig(); i++) {
			for (int j = 0; j < res.getCol(); j++) {
				res.chCase[i][j] = Mat.getCase(i, j).FMultiplication(parFrac);
			}
		}
		return res;
	}

	/**
	 * Multiplie deux lignes d'une même matrice (M[Li] <- M[Li] * M[Lj]) Le résultat
	 * va dans la première ligne donnée
	 * 
	 * @param i
	 *            la première ligne donnée
	 * @param j
	 *            la deuxième ligne donnée
	 * @return la multiplciation de deux lignes d'une même matrice
	 */
	public Matrice LMultiplication(int i, int j) {
		Matrice Mat = this;
		Fraction tabi[] = Mat.getLigne(i);
		Fraction tabj[] = Mat.getLigne(j);
		for (int x = 0; x < Mat.getCol(); x++) {
			tabi[x] = tabi[x].FMultiplication(tabj[x]);
		}
		Mat.setLigne(i, tabi);
		return Mat;
	}

	// pour DIVISION de deux matrice il faut calculer la matrice transpose, la
	// comatrice, le determinant et sous matrice
	// ces quatres choses permettent d'avoir la matrice inverse. Puis M1/M2 =
	// M1*(M2)^-1 (M1 multiplier par l'inverse de M2)
	// les divisions se feront donc avec FDivision
	// Impossible de faire ces quatres prerequis car il faut le faire le modulo
	// d'une fraction
	// Donc pas de "public Matrice MDivision(Matrice parMat) ( res <- M1 / M2 ) "

	/**
	 * Divise une matrice avec une fraction ( res <- M / frac )
	 * 
	 * @param parFrac
	 *            une fraction
	 * @return la division d'une matrice avec une fraction
	 */
	public Matrice iDivision(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(Mat.getLig(), Mat.getCol());
		for (int i = 0; i < res.getLig(); i++) {
			for (int j = 0; j < res.getCol(); j++) {
				res.chCase[i][j] = Mat.chCase[i][j].FDivision(parFrac);
			}
		}
		return res;
	}

	/**
	 * Additionne deux lignes d'une même matrice (M[Li] <- M[Li] / M[Lj]) Le
	 * résultat va dans la première ligne donnée
	 * 
	 * @param i
	 *            la première ligne donnée
	 * @param j
	 *            la deuxième ligne donnée
	 * @return l'addition de deux lignes d'une même matrice
	 */
	public Matrice lDivision(int i, int j) {
		Matrice Mat = this;
		Fraction tabi[] = Mat.getLigne(i);
		Fraction tabj[] = Mat.getLigne(j);
		for (int x = 0; x < Mat.getCol(); x++) {
			tabi[x] = tabi[x].FDivision(tabj[x]);
		}
		Mat.setLigne(i, tabi);
		return Mat;
	}

	/**
	 * Muttiplication d'une ligne par un nombre non nul. ( L3 -> 2L3 )
	 * 
	 * @param ligne
	 *            la ligne voulue
	 * @param pFraction
	 *            une fraction correspondant à un nombre non nul
	 */
	public void modifyLine(int ligne, Fraction pFraction) {
		for (int i = 0; i < getTaille(); i++) {
			for (int j = 0; j < getTaille(); j++) {
				if (ligne == i) {
					chCase[i][j] = chCase[i][j].FMultiplication(pFraction);
				} // if
			}
		}
	}

	/**
	 * Transforme une ligne à l'aide d'une autre ligne ( L3 -> L3 - 2L1 ) EXEMPLE
	 * pour : L3 -> L3 - 2L1 Ligne3 = Ligne3 - 2*Ligne1 matrice.modifyLine2(2, "-",
	 * 0, new Fraction(2));
	 * 
	 * @param ligneA
	 *            la première ligne à modifier
	 * @param operand
	 *            le signe opérateur
	 * @param ligneB
	 *            la deuxième ligne
	 * @param multiplicateur
	 *            le multiplicateur de la deuxième ligne
	 */
	public void modifyLine2(int ligneA, String operand, int ligneB, Fraction multiplicateur) {
		Fraction frac;
		for (int i = 0; i < getTaille(); i++) {
			for (int j = 0; j < getTaille(); j++) {
				if (ligneA == i) {
					frac = multiplicateur.FMultiplication(getCase(ligneB, j));
					if (operand.equals("+")) {
						chCase[i][j] = getCase(i, j).FAddition(frac);
					} else if (operand.equals("-")) {
						chCase[i][j] = getCase(i, j).FSoustraction(frac);
					} else if (operand.equals("*")) {
						chCase[i][j] = getCase(i, j).FMultiplication(frac);
					} else if (operand.equals("/")) {
						chCase[i][j] = getCase(i, j).FDivision(frac);
					} else {
						System.out.println("ERREUR L'OPERATION DEMANDEE N'EXISTE PAS");
					}
				}
			}
		}
	}

	@Override
	/**
	 * Affichage d'une matrice
	 */
	public String toString() {
		int tailleMatrice = this.getTaille(); // taille de la matrice carré
		int[][] TabFractionNumerateur = new int[tailleMatrice][tailleMatrice]; // tableau contenant les numerateur des
																				// fraction
		int[][] TabFractionDenominateur = new int[tailleMatrice][tailleMatrice]; // tableau contenant les denominateurs
																					// des fraction
		int[][] TailleMaxFraction = new int[tailleMatrice][tailleMatrice]; // le plus grand entre num et den
		int tailleMax = 7;
		String tirets = "";
		String espaces = "";
		String resultat = "";
		int correcteur = 0;

		for (int i = 0; i < this.getLig(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				TabFractionNumerateur[i][j] = this.getCase(i, j).getNumerateur();
				TabFractionDenominateur[i][j] = this.getCase(i, j).getDenominateur();
				TailleMaxFraction[i][j] = Math.max(String.valueOf(TabFractionNumerateur[i][j]).length(),
						String.valueOf(TabFractionDenominateur[i][j]).length());
			}
		}

		for (int i = 0; i < tailleMatrice; i++) {
			for (int k = 0; k < 3; k++) {
				for (int j = 0; j < tailleMatrice; j++) {
					if (TabFractionNumerateur[i][j] < 0 || TabFractionDenominateur[i][j] < 0) {
						correcteur += 1;
					}
					if (TabFractionDenominateur[i][j] == 1) {
						if (k % 3 == 0) {
							for (int x = 0; x < tailleMax; x++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces);
						} else if (k % 3 == 1) {
							if (correcteur > 1) {
								espaces = espaces.concat(" ");
								correcteur = 0;
							}
							for (int y = 0; y < (tailleMax
									- String.valueOf(TabFractionNumerateur[i][j]).length()); y++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces + TabFractionNumerateur[i][j]);
						} else if (k % 3 == 2) {
							for (int z = 0; z < tailleMax; z++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces);
						}
					} else {
						if (k % 3 == 0) {
							if (correcteur > 1) {
								espaces = espaces.concat(" ");
								correcteur = 0;
							}
							for (int x = 0; x < (tailleMax
									- String.valueOf(TabFractionNumerateur[i][j]).length()); x++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces + TabFractionNumerateur[i][j]);
						} else if (k % 3 == 1) {
							for (int y = 0; y < TailleMaxFraction[i][j]; y++) {
								tirets = tirets.concat("–");
							}
							for (int y = 0; y < (tailleMax - TailleMaxFraction[i][j]); y++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces + tirets);
						} else if (k % 3 == 2) {
							for (int z = 0; z < (tailleMax
									- String.valueOf(TabFractionDenominateur[i][j]).length()); z++) {
								espaces = espaces.concat(" ");
							}
							resultat = resultat.concat(espaces + TabFractionDenominateur[i][j]);
						}
					}
					espaces = "";
					tirets = "";
				}
				correcteur = 0;
				resultat = resultat.concat("\n");
			}
			resultat = resultat.concat("\n");
		}

		return resultat;
	}

	public String toStringLigne(int parLigne) {
		Fraction[] ligne = new Fraction[this.getTaille()];
		for (int i = 0; i < this.getTaille(); i++) {
			for (int j = 0; j < this.getTaille(); j++) {
				if (i == parLigne) {
					ligne[j] = this.getCase(1, j);
				}
			}
		}

		int tailleMatrice = this.getCol(); // taille de la matrice 1 sur colonne
		int[] TabFractionNumerateur = new int[tailleMatrice]; // tableau contenant les numerateur des fraction
		int[] TabFractionDenominateur = new int[tailleMatrice]; // tableau contenant les denominateurs des fraction
		int tailleMax = 7;
		String espaces = "";
		String resultat = "";

		for (int j = 0; j < this.getCol(); j++) {
			TabFractionNumerateur[j] = this.getCase(parLigne, j).getNumerateur();
			TabFractionDenominateur[j] = this.getCase(parLigne, j).getDenominateur();
		}

		for (int j = 0; j < tailleMatrice; j++) {
			if (TabFractionNumerateur[j] < 0 || TabFractionDenominateur[j] < 0) {
				espaces = espaces.concat(" ");
			}
			if (TabFractionDenominateur[j] == 1) {
				for (int x = 0; x < (tailleMax - String.valueOf(TabFractionNumerateur[j]).length()); x++) {
					espaces = espaces.concat(" ");
				}
				resultat = resultat.concat(espaces + TabFractionNumerateur[j]);
			} else {
				for (int x = 0; x < (tailleMax - (String.valueOf(TabFractionNumerateur[j]).length() + 1
						+ String.valueOf(TabFractionDenominateur[j]).length())); x++) {
					espaces = espaces.concat(" ");
				}
				espaces = espaces.concat(" ");
				resultat = resultat.concat(espaces + TabFractionNumerateur[j] + "/" + TabFractionDenominateur[j]);
			}
			espaces = "";
		}
		return resultat;
	}

	public String toString2() {
		String chaine = new String();
		chaine += "\n\n";

		for (int i = 0; i < chLigne; i++) {
			for (int j = 0; j < chCol; j++) {
				chaine += chCase[i][j];
				for (int espace = 0; espace < 10 - chCol; espace++) {
					chaine += " ";
				}

			}
			chaine += "\n\n";
		}

		return chaine;
	}
}