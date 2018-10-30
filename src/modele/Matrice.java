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
		this.remplir();
	}
	
	//creer une matrice vide de taille parLig et parCol
	public Matrice (int parLig, int parCol) {
		Lig = parLig;
		Col = parCol;
		Case = new Fraction[Lig][Col];
		this.remplir();
	}
	
	//creer une matrice avec un tableau donne
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
	//remplie une matrice de 0 a sa creation
	public void remplir() {
		for(int i=0; i<this.getLig() ; i++) {
			for(int j=0; j<this.getCol() ;j++) {
				this.Case[i][j] = new Fraction(0);
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
	//get toute la matrice sous forme de tableau de fraction
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
	
	//echange deux lignes entre elles
	public void Echange(int i, int j) {
		Fraction[] tampon = getLigne(i);
        Case[i] = getLigne(j);
        Case[j] = tampon;
    }
	
	//COMPARE
	//compare deux matrices entre elle et renvoie un boolean
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
	//return true si la matrice est une matrice identite
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
	//additionne deux matrices entre elles
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
	//additionne une matrice avec une fraction
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
	
	//SOUSTRACTION
	//soustrait deux matrices entre elles
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
	//soustrait une matrice avec une fraction
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
	
	//MULTIPLICATION
	//multiplie deux matrices entre elles
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
	//multiplie une matrice avec une fraction
	public Matrice IMultiplication(Fraction parFrac) {
		Matrice Mat = this;
		Matrice res = new Matrice(this.getLig(),this.getCol());
		for (int i=0; i < res.getLig(); i++) {
			for(int j=0; j < res.getCol() ; j++) {
				for(int k=0; k < Mat.getCol() ; k++) {
					res.Case[i][j] = (res.getCase(i, j)).FAddition((Mat.getCase(i,k).FMultiplication(parFrac)));
				}
			}
		}
		return res;
	}
	
	//pour DIVISION de matrice il faut calculer la matrice transpose, la comatrice, le determinant et sous matrice
	//ces quatres choses permettent d'avoir la matrice inverse. Puis M1/M2 = M1*(M2)^-1  (M1 multiplier par l'inverse de M2)
	//las divisions se feront donc avec FDivision
	//Impossible de faire ces quatres prerequis car il faut le faire le modulo d'une fraction
	
	//AFFICHE
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
		
		Matrice A = new Matrice(3,3);
		A.Affiche();
		System.out.println();
		
		Fraction[][] tab = {{new Fraction(1,2),new Fraction(2,2),new Fraction(3,2)},{new Fraction(4,2),new Fraction(5,2),new Fraction(6)},{new Fraction(7),new Fraction(8),new Fraction(9)}};
		Matrice B = new Matrice(tab);
		B.Affiche();
		System.out.println("getLigne()");
		Fraction tab2[] = B.getLigne(1);
		System.out.println(tab2[0] + " | " + tab2[1] + " | " + tab2[2]);
		System.out.println("setLigne()");
		Fraction[] tab3 = {new Fraction(11), new Fraction(12), new Fraction(13,14)};
		B.setLigne(1,tab3);
		B.Affiche();
		System.out.println();
		
		Matrice C = new Matrice(B);
		C.Affiche();
		System.out.println();
		
		Matrice D = Matrice.Identite(3);
		Matrice E = Matrice.Identite(3);
		D.Affiche();
		System.out.println(D.isIdentite());
		System.out.println(D.MCompare(E));
		System.out.println();
		
		System.out.println("A+(1/2)");
		A.IAddition(new Fraction(1,2)).Affiche();;
		System.out.println();
		
		System.out.println("A+B");
		A.MAddition(B).Affiche();
		System.out.println();
		
		System.out.println("A-B");
		A.MSoustraction(B).Affiche();
		System.out.println();
		
		System.out.println("A*B");
		A.MMultiplication(B).Affiche();
		System.out.println();
		
		C.Echange(1,2);
		C.Affiche();
		System.out.println();
	}
}