package controleur;

import javafx.scene.input.MouseEvent;
import modele.Dessin;
import modele.Figure;
import vue.PanneauDeDessin;

public abstract class EcouteurSouris {
	protected Dessin dessin;
	Figure f;
	PanneauDeDessin panneau;
	double x, y;
	EcouteurSouris(Dessin d, PanneauDeDessin p) {
		dessin = d;
		panneau = p;
	}
	public void onMousePressed(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
	void onMouseMoved(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
	public void onMouseDragged(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
}
