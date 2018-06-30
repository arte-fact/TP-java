package controleur;

import javafx.scene.input.MouseEvent;
import modele.Chargement;
import modele.Dessin;
import modele.Figure;
import vue.PanneauDeDessin;

public abstract class EcouteurSouris {
	protected Dessin dessin;
	protected Figure f;
	protected PanneauDeDessin panneau;
	protected double x, y;
	public EcouteurSouris(Dessin d, PanneauDeDessin p) {
		dessin = d;
		panneau = p;
	}
	public void onMousePressed(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
	public void onMouseMoved(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
	public void onMouseDragged(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
}
