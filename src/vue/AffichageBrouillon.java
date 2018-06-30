package vue;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import modele.Dessin;
import modele.Etoile;
import modele.Figure;
import modele.Operation;
import modele.Trace;

public class AffichageBrouillon implements Operation {
	private GraphicsContext gc;
	public void opereSur(Dessin d) {
		for(Figure f : d) f.realise(this);
	}
	public void opereSur(Trace t) {
    	gc.setStroke(Color.BLACK);
    	gc.setLineWidth(1);
		double x, y;
    	Point2D pt;
    	IterateurBrouillon it = new IterateurBrouillon(t.iterator());
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
		IterateurBrouillon it = new IterateurBrouillon(e.iterator());
    	gc.setStroke(Color.BLACK);
    	gc.setLineWidth(1);
		double xC = e.centre().getX(), yC = e.centre().getY();
		Point2D pt;
		while (it.hasNext()){
		    pt = it.next();
            gc.strokeLine(xC, yC, pt.getX(), pt.getY());
        }
	}
}
