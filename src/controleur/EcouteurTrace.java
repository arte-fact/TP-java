package controleur;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modele.Dessin;
import modele.Trace;
import vue.PanneauDeDessin;

public class EcouteurTrace extends EcouteurSouris {
	public EcouteurTrace(Dessin d, PanneauDeDessin p) {
		super(d, p);
	}
	public void onMousePressed(MouseEvent evt) {
		super.onMousePressed(evt);
        if (evt.getButton() == MouseButton.PRIMARY) {
            x = evt.getX();
            y = evt.getY();
            f = new Trace(panneau.couleur(), panneau.epaisseur(), x, y);
            dessin.ajoute(f);
    		panneau.miseAJourTitre();
        }
	}
	public void onMouseDragged(MouseEvent evt) {
		super.onMouseDragged(evt);
        if (evt.getButton() == MouseButton.PRIMARY) {
            panneau.dessineTrait(x, y, evt.getX(), evt.getY());
            x = evt.getX(); y = evt.getY();
            f.ajoute(x, y);
        }
	}
}
