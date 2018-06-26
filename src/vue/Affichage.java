package vue;

import java.util.Iterator;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import modele.Dessin;
import modele.Etoile;
import modele.Figure;
import modele.Operation;
import modele.Trace;

public class Affichage implements Operation {
	private GraphicsContext gc;
	public Affichage(GraphicsContext context) {
		gc = context;
	}
	public void opereSur(Dessin d) {
		for(Figure f : d) f.realise(this);
	}
	public void opereSur(Trace t) {
    	gc.setStroke(t.couleur());
    	gc.setLineWidth(t.epaisseur());
		double x, y;
    	Point2D pt;
    	Iterator<Point2D> it = t.iterator();
    	pt = it.next();
    	x = pt.getX();
    	y = pt.getY();
    	while (it.hasNext()) {
    		pt = it.next();
    		gc.strokeLine(x, y, pt.getX(), pt.getY());
    		x = pt.getX();
        	y = pt.getY();
    	}
	}
	public void opereSur(Etoile e) {
		gc.setStroke(e.couleur());
    	gc.setLineWidth(e.epaisseur());
		double xC = e.centre().getX(), yC = e.centre().getY();
    	for(Point2D pt : e) {
    		gc.strokeLine(xC, yC, pt.getX(), pt.getY());
    	}
	}
}
