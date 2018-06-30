package modele;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ChargementV1 implements Operable, Iterable<Figure> {
    private List<Figure> contenu;
	private boolean modifie;
	private File fichier;
	private Dessin dessin;

    public ChargementV1(Dessin d) {
        contenu = new LinkedList<Figure>();
        fichier = null;
        modifie = false;
        dessin = d;
    }
    public void realise(Operation op) {
    	op.opereSur(dessin);
    }
    public Iterator<Figure> iterator() {
        return contenu.iterator();
    }

    void chargeDepuis(DataInputStream dis) throws Exception {
        int nbFig = dis.readInt();
        for(int i=0; i<nbFig; i++) {
            byte type = dis.readByte();
            Figure fig;
            switch(type) {
                case  1: fig = new Trace(); break;
                case  2: fig = new Etoile(); break;
                default: fig = null;
            }
            if (fig != null) {
                fig.chargeDepuis(dis);
            }
            dessin.ajoute(fig);
        }
        modifie = false;
    }

    public List<Figure> getContenu() {
        return contenu;
    }
}
