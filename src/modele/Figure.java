package modele;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Figure implements Operable, Iterable<Point2D> {
    protected Color couleur;
    protected int epaisseur;
    protected List<Point2D> contenu;
    public Figure() {
        couleur = Color.BLACK;
        epaisseur = 1;
    	contenu = new LinkedList<Point2D>();
    }
    public Figure(Color couleur, int epaisseur) {
        this.couleur = couleur;
        this.epaisseur = epaisseur;
        contenu = new LinkedList<Point2D>();
    }
    public Color couleur() {
        return couleur;
    }
    public void couleur(Color c) {
    	couleur = c;
    }
    public int epaisseur() {
    	return epaisseur;
    }
    public void epaisseur(int epaisseur) {
    	this.epaisseur = epaisseur;
    }
    public int nombreDElements() {
        return contenu.size();
    }
    public void ajoute(double x, double y) {
        contenu.add(new Point2D(x, y));
    }
    public Point2D element(int index) {
        return contenu.get(index);
    }
    public Iterator<Point2D> iterator() {
        return contenu.iterator();
    }
    public abstract void enregistreDans(DataOutputStream fichier) throws Exception;
    public abstract void chargeDepuis(DataInputStream fichier) throws Exception;
}
