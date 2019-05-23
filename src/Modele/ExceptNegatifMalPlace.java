package Modele;

/**
 * ExceptEntreFraction est une classe qui va permettre de g�rer le cas o� le
 * signe "-" est mal plac�
 */
public class ExceptNegatifMalPlace extends Exception {

	/**
	 * Cl� de hachage SHA qui identifie de mani�re unique ExceptNegatifMalPlace
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception qui va �tre lev�e quand le signe "-" est mal plac� dans la fraction
	 */
	public ExceptNegatifMalPlace() {
	}
}