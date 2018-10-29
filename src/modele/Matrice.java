package modele;

public class Matrice {
	public int taille;
	public int value[][];
	
	// CREATE MATRICE
	// prend en paramètre - la taille de la matrice - une liste de int
	// Remplis la matrice des éléments de la liste de int
	public Matrice(int parTaille, int liste[]){
		taille = parTaille;
		value = new int[taille][taille];
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
	public int[][] getValue() {
		return value;
	}
	// SETTER VALUE
	public void setValue(int pValue, int ligne, int col) {
		value[ligne][col] = pValue;
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
		for(int i=0; i<getTaille(); i++) {
			for(int j=0; j<getTaille(); j++) {
				if(j==zero && value[i][j]!=1) {
					return false;
				}
				else if(j!=zero && value[i][j]!=0) {
					return false;
				}
			}
			zero+=1;
		}
		return true;
	}
	
	// CHANGE LINE
	// operand possible - add - substract - multiply - divide
	public void changeLine(int ligne, String operand) {
		if(operand == "add") {
			// ...
		}
		else if(operand == "substract") {
			// ...
		}
		else if(operand == "multiply") {
			// ...
		}
		else if(operand == "divide") {
			// ...
		}
	}
	
	
public static void main(String []args) {
		
		int l_un[] = {98,85,74,65,45,32,21,10,2}; 
		int l_deux[] = {12,21,31,24,14,94,66,36,46}; 
		int l_ident[] = {1,0,0,0,1,0,0,0,1}; 
		
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
		ident.setValue(5, 1, 1);
		ident.toString();
		if(ident.isIdentity())
			System.out.println("C'est une matrice identite");
		else
			System.out.println("Ce n'est PAS une matrice identite");
		
	}// MAIN
	
	

}//class Matrice