package vue;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import modele.Dessin;

public class ZoneDeDessin extends Canvas {
    private Dessin dessin;
    private Color couleur;
    private int epaisseur;
    public ZoneDeDessin(Dessin d) {
        super();
        dessin = d;
    }
    public void changeCouleur(Color nouvelleCouleur) {
        couleur = nouvelleCouleur;
    }
    public Color couleur() {
        return couleur;
    }
    public void changeEpaisseur(int nouvelleEpaisseur) {
        epaisseur = nouvelleEpaisseur;
    }
    public int epaisseur() {
        return epaisseur;
    }
    public void dessineTrait(double x1, double y1, double x2, double y2) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setLineWidth(epaisseur);
        gc.setStroke(couleur);
        gc.strokeLine(x1, y1, x2, y2);
    }
    private void dessine(Dessin d, GraphicsContext g) {
    	g.setFill(Color.WHITE);
        g.fillRect(0,  0, getWidth(), getHeight());
        d.realise(new Affichage(g));
    }
    public void dessineContenu() {
        dessine(dessin, getGraphicsContext2D());
    }
}
