package modele;

import Factory.FabriqueChargementDessin;
import Factory.FabriqueEnregistrementDessin;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Dessin implements Operable, Iterable<Figure> {

    private List<Figure> contenu;
	private boolean modifie;
	private File fichier;
	private FabriqueChargementDessin fabriqueChargementDessin;
	private FabriqueEnregistrementDessin fabriqueEnregistrementDessin;
	private byte version;

    public Dessin() {
        contenu = new LinkedList<Figure>();
        fichier = null;
        modifie = false;
        fabriqueChargementDessin = new FabriqueChargementDessin();
        fabriqueEnregistrementDessin = new FabriqueEnregistrementDessin();
        version = 2;
    }
    public void ajoute(Figure f) {
        contenu.add(f);
        modifie = true;
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

    void setModifie(Boolean b) {
        this.modifie = b;
    }


    public OuvertureDessin charge(File f) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            vider();
            fichier = f;
            version = dis.readByte();
            switch (version) {
                case 1:
                    fabriqueChargementDessin.getChargementV1(this).chargeDepuis(dis);
                case 2:
                    fabriqueChargementDessin.getChargementV2(this).chargeDepuis(dis);
            }
            return OuvertureDessin.REUSSIE;
        } catch (ExceptionVersionInconnue e1) {
            return OuvertureDessin.VERSION_INCONNUE;
        } catch (Exception e) {
            return OuvertureDessin.PROBLEME_PENDANT_LECTURE;
        }
    }

    public String nomDeFichier() {
        String nom = (modifie) ? "*" : "";
        nom += (fichier == null) ? "Sans nom" : fichier.getName();
        return nom;
    }

    public File getFichier() {
        return fichier;
    }

    public List<Figure> getContenu() {
        return contenu;
    }

    public boolean enregistre (File f) {
        fichier = f;
        switch (version) {
            case 1:
                return fabriqueEnregistrementDessin.getEnregistrementV1().enregistre(this);
            case 2:
                return fabriqueEnregistrementDessin.getEnregistrementV2().enregistre(this);
            default:
                return false;
        }
    }
}
