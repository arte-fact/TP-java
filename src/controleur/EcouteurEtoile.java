package controleur;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modele.Dessin;
import modele.Etoile;
import vue.PanneauDeDessin;

public class EcouteurEtoile extends EcouteurSouris {
	public EcouteurEtoile(Dessin d, PanneauDeDessin p) {
		super(d, p);
	}
	public void onMousePressed(MouseEvent evt) {
		super.onMousePressed(evt);
        if (evt.getButton() == MouseButton.PRIMARY) {
            x = evt.getX();
            y = evt.getY();
            f = new Etoile(panneau.couleur(), panneau.epaisseur(), x, y);
            dessin.ajoute(f);
    		panneau.miseAJourTitre();
        }
	}
	public void onMouseDragged(MouseEvent evt) {
		super.onMouseDragged(evt);
        if (evt.getButton() == MouseButton.PRIMARY) {
            panneau.dessineTrait(x, y, evt.getX(), evt.getY());
            f.ajoute(evt.getX(), evt.getY());
        }
	}
}
