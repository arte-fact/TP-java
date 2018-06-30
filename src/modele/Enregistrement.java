package modele;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Enregistrement implements Operable, Iterable<Figure> {
    private List<Figure> contenu;
	private File fichier;
	private Dessin dessin;


    public Enregistrement(Dessin c) {
        contenu = new LinkedList<Figure>();
        fichier = null;
        dessin = c;
    }
    public void realise(Operation op) {
    	op.opereSur(dessin);
    }
    public Iterator<Figure> iterator() {
        return contenu.iterator();
    }
    public boolean enregistre(Chargement c) {
        return enregistreSous(c.fichier);
    }
    public boolean enregistreSous(File f) {
    // renvoie vrai en cas de succ�s, faux en cas d'�chec
        fichier = f;
        try {
            DataOutputStream dos;
            dos = new DataOutputStream(new FileOutputStream(f));
            dos.writeByte(1);
            enregistreDans(dos);
            dos.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    private void enregistreDans(DataOutputStream dos) throws Exception {
        dos.writeInt(contenu.size());
        for (Figure fig : contenu) {
            fig.enregistreDans(dos);
        }
        dessin.setModifie(false);
    }
}
