package modele;

public class Matrice {
	
	private int Lig;
	private int Col;
	private Fraction[][] Case;
	//une case non remplie signifie un zero dans cette meme case
	//pour l'instant les cases sont remplites avec des doubles, mais elles devront plus tard etre remplites avec des fractions, en sachant que 3 est tout simplement 3/1 mais simplifiee
	
	//MATRICE
	//creer une matrice carre vide de taille parTaille
	public Matrice (int parTaille) {
		Lig = parTaille;
		Col = parTaille;
		Case = new Fraction[Lig][Col];
		remplir(Case);
	}
	
	//creer une matrice vide de taille parLig et parCol
	public Matrice (int parLig, int parCol) {
		Lig = parLig;
		Col = parCol;
		Case = new Fraction[Lig][Col];
		remplir(Case);
	}
	
	//creer une matrice avec un tableau donne
	public Matrice (Fraction[][] parMat) {
		Lig = parMat.length;
		Col = parMat.length;
		this.Case = new Fraction[Lig][Col];
		for(int i=0; i<Lig ; i++) {
			for(int j=0; j<Col ;j++) {
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
	public void remplir(Fraction[][] Mat) {
		for(int i=0; i<Lig ; i++) {
			for(int j=0; j<Col ;j++) {
				this.Case[i][j] = new Fraction(0);
			}
		}
	}
	
	//GETTER
	//get une case
	public Fraction getCase(int i, int j) {
		return this.Case[i][j];
	}
	//get une ligne sous forme de tableau
	/*public double[] getLigne(int i) {
		return ;
	}*/
	public int getTaille() {
		if(Lig != Col) {
			throw new RuntimeException("Pas matrice carre");
		}
		else {
			return Lig;
		}
	}
	public int getLig() {
		return Lig;
	}
	public int getCol() {
		return Col;
	}
	
	//SETTER
	//set une case
	public void setCase(int i, int j, Fraction var) { //var int a changer en fraction
		this.Case[i][j] = var;
	}
	//ser une ligne sous forme de tableau
	/*public void setLigne(int i, int[] tab) {
		
	}*/
	
	//FONCTION
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
		Fraction[] tampon = Case[i];
        Case[i] = Case[j];
        Case[j] = tampon;
    }
	
	//COMPARE
	//compare deux matrices entre elle et renvoie un boolean
	public boolean MCompare(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.Lig != parMat.Lig || Mat.Col != parMat.Col) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		for (int i=0; i < Lig; i++) {
			for(int j=0; j < Col ;j++) {
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
		for(int i=0; i < Lig; i++) {
			for(int j=0; j < Col; j++) {
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
	
	//OPERATION
	//ADDITION
	//additionne deux matrices entre elles
	public Matrice MAddition(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.Lig != parMat.Lig || Mat.Col != parMat.Col) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		Matrice res = new Matrice(Lig,Col);
		for (int i=0; i < Lig; i++) {
			for(int j=0; j < Col ;j++) {
				res.Case[i][j] = Mat.Case[i][j].FAddition(parMat.Case[i][j]);
			}
		}
		return res;
	}
	
	//SOUSTRACTION
	//soustrait deux matrices entre elles
	public Matrice MSoustraction(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.Lig != parMat.Lig || Mat.Col != parMat.Col) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		Matrice res = new Matrice(Lig,Col);
		for (int i=0; i < Lig; i++) {
			for(int j=0; j < Col ;j++) {
				res.Case[i][j] = Mat.Case[i][j].FSoustraction(parMat.Case[i][j]);
			}
		}
		return res;
	}
	
	//MULTIPLICATION
	//multiplie deux matrices entre elles
	public Matrice MMultiplication(Matrice parMat) {
		Matrice Mat = this;
		if(Mat.Col != parMat.Lig) {
			throw new RuntimeException("Erreur dimensions des deux matrices");
		}
		Matrice res = new Matrice(Mat.Lig,parMat.Col);
		for (int i=0; i < res.Lig; i++) {
			for(int j=0; j < res.Col ; j++) {
				for(int k=0; k < Mat.Col ; k++) {
					res.Case[i][j] = res.Case[i][j].FAddition((Mat.Case[i][k].FAddition(parMat.Case[k][j])));
				}
			}
		}
		return res;
	}
	
	//pour DIVISION de matrice il faut calculer la matrice transpose, la comatrice, le determinant et sous matrice
	//ces quatres choses permettent d'avoir la matrice inverse. Puis M1/M2 = M1*(M2)^-1  (iverse de M2)
	//la division se feront donc avec FDivision
	
	//TOSTRING
	//affiche une matrice
	public void Affiche() {
		for (int i=0; i < Lig; i++) {
			for(int j=0; j < Col ;j++) {
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
		System.out.println();
		
		Matrice C = new Matrice(B);
		C.Affiche();
		System.out.println();
		
		Matrice D = Matrice.Identite(4);
		Matrice E = Matrice.Identite(4);
		D.Affiche();
		System.out.println(D.isIdentite());
		System.out.println(D.MCompare(E));
		System.out.println();
		
		A.MAddition(B).Affiche();
		System.out.println();
		
		A.MSoustraction(B).Affiche();
		System.out.println();
		
		A.MMultiplication(B).Affiche();
		System.out.println();
		
		C.Echange(1,2);
		C.Affiche();
		System.out.println();
	}
}