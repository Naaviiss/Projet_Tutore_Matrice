package Modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LectureEcriture {
	/**
	 * Permet la lecture d'un fichier
	 * @param File parFichier
	 * @return Object objetlu
	 */
	public static Object lecture (File parFichier) {
		ObjectInputStream flux;
		Object objetLu = null;
		
		try {
			flux = new ObjectInputStream(new FileInputStream (parFichier));
			objetLu=(Object)flux.readObject();
			flux.close();
		}
		
		catch (ClassNotFoundException parException) {
			System.err.println(parException.toString());
			System.exit(1);
		}
		
		catch (IOException parException) {
			System.err.println("Erreur lecture du fichier " + parException.toString());
			System.exit (1);
		}
		
		return objetLu;
	}
	
	/**
	 * Permet l'écriture dans un fichier
	 * @param File parFichier
	 * @param Object parObjet
	 */
	public static void ecriture (File parFichier, Object parObjet) {
		ObjectOutputStream flux=null;
		
		try {
			flux=new ObjectOutputStream(new FileOutputStream(parFichier));
			flux.writeObject(parObjet);
			flux.flush();
			flux.close();
		}
		
		catch (IOException parException) {
			System.err.println ("Probleme a l'ecriture\n" + parException.toString());
			System.exit(1);
		}
	}
}