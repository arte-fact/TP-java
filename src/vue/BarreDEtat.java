package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class BarreDEtat extends GridPane {
	private Label coord, outil, epaisseur, couleur;
	public BarreDEtat() {
		super();
		coord = new Label();
		add(coord, 0, 0);
		outil = new Label();
		add(outil, 1, 0);
		epaisseur = new Label();
		add(epaisseur, 2, 0);
		couleur = new Label();
		add(couleur, 3, 0);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(25);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(25);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setPercentWidth(25);
		getColumnConstraints().addAll(col1,col2,col3,col4);
	}
	public void afficheCoordonnees(double x, double y) {
		String texte = "Pointeur : " + (int)x + ", " + (int)y;
		coord.setText(texte);
	}
	public void afficheOutil(String nomOutil) {
		String texte = "Outil : " + nomOutil;
		outil.setText(texte);
	}
	public void afficheEpaisseur(int e) {
		String texte = "Epaisseur : " + e;
		epaisseur.setText(texte);
	}
	public void afficheCouleur(String nomCouleur) {
		String texte = "Couleur : " + nomCouleur;
		couleur.setText(texte);
	}
}
