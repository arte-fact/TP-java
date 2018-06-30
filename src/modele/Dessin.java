package modele;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Dessin implements Operable, Iterable<Figure> {
    private List<Figure> contenu;
	private boolean modifie;
	public File fichier;
    public Dessin() {
        contenu = new LinkedList<Figure>();
        fichier = null;
        modifie = false;
    }
    public void ajoute(Figure f) {
        contenu.add(f);
        modifie = true;
    }
    public int nombreDElements() {
        return contenu.size();
    }
    public Figure element(int index) {
        return contenu.get(index);
    }
    public void retire(int index) {
        contenu.remove(index);
    }
    public void vider() {
    	contenu.clear();
        modifie = false;
        fichier = null;
    }
    public void realise(Operation op) {
    	op.opereSur(this);
    }
    public Iterator<Figure> iterator() {
        return contenu.iterator();
    }

    public boolean enFichier() {
    	return fichier != null;
    }
    public boolean modifie() {
    	return modifie;
    }

    public void setModifie(Boolean b) {
        this.modifie = b;
    }
}
