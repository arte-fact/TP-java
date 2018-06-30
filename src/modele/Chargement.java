package modele;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Chargement implements Operable, Iterable<Figure> {
    private List<Figure> contenu;
	private boolean modifie;
	public File fichier;
	private Dessin dessin;

    public Chargement(Dessin d) {
        contenu = new LinkedList<Figure>();
        fichier = null;
        modifie = false;
        dessin = d;
    }
    public void vider() {
    	contenu.clear();
        modifie = false;
        fichier = null;
    }
    public void realise(Operation op) {
    	op.opereSur(dessin);
    }
    public Iterator<Figure> iterator() {
        return contenu.iterator();
    }
    public OuvertureDessin charge(File f) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))){
            vider();
            byte version = dis.readByte();
            if (version != 1) throw new ExceptionVersionInconnue(version);
            chargeDepuis(dis);
            fichier = f;
            return OuvertureDessin.REUSSIE;
        } catch(ExceptionVersionInconnue e1) {
        	return OuvertureDessin.VERSION_INCONNUE;
        } catch(Exception e) {
            return OuvertureDessin.PROBLEME_PENDANT_LECTURE;
        }
    }
    private void chargeDepuis(DataInputStream dis) throws Exception {
        int nbFig = dis.readInt();
        for(int i=0; i<nbFig; i++) {
            byte type = dis.readByte();
            Figure fig;
            switch(type) {
                case  1: fig = new Trace(); break;
                case  2: fig = new Etoile(); break;
                default: fig = null;
            }
            fig.chargeDepuis(dis);
            dessin.ajoute(fig);
        }
        modifie = false;
    }
    public String nomDeFichier() {
    	String nom = (modifie) ? "*" : "";
   		nom += (fichier == null) ? "Sans nom" : fichier.getName();
    	return nom;
    }
    public boolean enFichier() {
    	return fichier != null;
    }
    public boolean modifie() {
    	return modifie;
    }
}
