package modele;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Dessin implements Operable, Iterable<Figure> {
    private List<Figure> contenu;
	private boolean modifié;
	private File fichier;
    public Dessin() {
        contenu = new LinkedList<Figure>();
        fichier = null;
        modifié = false;
    }
    public void ajoute(Figure f) {
        contenu.add(f);
        modifié = true;
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
        modifié = false;
        fichier = null;
    }
    public void realise(Operation op) {
    	op.opereSur(this);
    }
    public Iterator<Figure> iterator() {
        return contenu.iterator();
    }
    public boolean enregistre() {
        return enregistreSous(fichier);
    }
    public boolean enregistreSous(File f) {
    // renvoie vrai en cas de succès, faux en cas d'échec
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
    private void enregistreDans(DataOutputStream dos) throws Exception {
        dos.writeInt(contenu.size());
        for(Figure fig:contenu) {
            fig.enregistreDans(dos);
        }
        modifié = false;
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
            contenu.add(fig);
        }
        modifié = false;
    }
    public String nomDeFichier() {
    	String nom = (modifié) ? "*" : "";
   		nom += (fichier == null) ? "Sans nom" : fichier.getName();
    	return nom;
    }
    public boolean enFichier() {
    	return fichier != null;
    }
    public boolean modifié() {
    	return modifié;
    }
}
