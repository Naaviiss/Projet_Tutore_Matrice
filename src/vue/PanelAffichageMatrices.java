package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.*;

public class PanelAffichageMatrices extends JPanel{

	private JTable tableMatrices; //String pour l'instant
	private HashMap<Matrice, Matrice> chMatrices; //String pour l'instant
	private List<String> chLigneModif;//pour les calculs effectués
	private List<String> chCommentaire;//pour les commentaires
	
	public PanelAffichageMatrices(List<String> pLigneModif,List<String> pCommentaire) {
		
		chMatrices = new HashMap<Matrice, Matrice>();
		chLigneModif = pLigneModif;
		chCommentaire = pCommentaire;
		tableMatrices = new JTable();
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chLigneModif,chCommentaire));
		
		MultiLigneRenderer renderer = new MultiLigneRenderer(); //renderer pour faire du multiligne
		tableMatrices.getColumnModel().getColumn(0).setCellRenderer(renderer);
		tableMatrices.getColumnModel().getColumn(1).setCellRenderer(renderer);
		
		//intitules des colonnes
		tableMatrices.getTableHeader().setBackground(new Color(205, 0, 0));
		tableMatrices.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,20));
		
		//empecher les redimensionnements et réordonnancements
		tableMatrices.getTableHeader().setResizingAllowed(false);
		tableMatrices.getTableHeader().setReorderingAllowed(false);
		
		//hauteur des lignes
		tableMatrices.setRowHeight(180);
		
		//taille des colonnes et de la table
		tableMatrices.getColumnModel().getColumn(0).setPreferredWidth(240);
		tableMatrices.getColumnModel().getColumn(1).setPreferredWidth(240);
		tableMatrices.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableMatrices.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		//scrollbar
		JScrollPane panDefil = new JScrollPane(tableMatrices,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableMatrices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panDefil.setPreferredSize(new Dimension(900, 850));
		
		tableMatrices.setDefaultRenderer(Matrice.class, new MultiLigneRenderer());

		//ajout panneau defilant avec la table
		this.add(panDefil);
	}
	
	public void ajoutMatrice(Matrice M1, Matrice M2) {
		System.out.println(M1.toString()+"--------\n"+M2.toString());
		chMatrices.put(M1,M2);
		Set<Matrice> cles = chMatrices.keySet();
		Iterator<Matrice> it = cles.iterator();
		while(it.hasNext()) {
			Object cle = it.next();
			Object valeur = chMatrices.get(cle);
		}
		tableMatrices.repaint();
	}

	public HashMap<Matrice, Matrice> getChMatrices() {
		return chMatrices;
	}

	public void setChMatrices(HashMap<Matrice, Matrice> chMatrices) {
		this.chMatrices = chMatrices;
	}
	
	public void setTable(){
		//Méthode qui permet d'actualiser
		//tableMatrices.setDefaultRenderer(Matrice.class, new MultiLigneRenderer());
		tableMatrices.setModel(new ModelAffichageMatrices(chMatrices,chLigneModif,chCommentaire));
	}//setTable()
}
