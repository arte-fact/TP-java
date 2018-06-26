package modele;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Trace extends Figure {
    public Trace(Color coul, int epaiss, double x, double y) {
        super(coul, epaiss);
        ajoute(x, y);
    }
    public Trace() {
    	super();
    }
    public void realise(Operation op) {
    	op.opereSur(this);
    }
    public void enregistreDans(DataOutputStream fichier) throws Exception {
        fichier.writeByte(1);
        fichier.writeInt(epaisseur);
        fichier.writeDouble(couleur.getRed());
        fichier.writeDouble(couleur.getGreen());
        fichier.writeDouble(couleur.getBlue());
        fichier.writeInt(contenu.size());
        for(Point2D pt:contenu) {
            fichier.writeDouble(pt.getX());
            fichier.writeDouble(pt.getY());
        }
    }
    public void chargeDepuis(DataInputStream fichier) throws Exception {
        epaisseur = fichier.readInt();
        double r, v, b;
        r = fichier.readDouble();
        v = fichier.readDouble();
        b = fichier.readDouble();
        couleur = Color.color(r, v, b);
        int nbPts = fichier.readInt();
        for(int i=0; i<nbPts; i++) {
            double x = fichier.readDouble();
            double y = fichier.readDouble();
            ajoute(x, y);
        }
    }
}
