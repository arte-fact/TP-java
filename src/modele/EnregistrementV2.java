package modele;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EnregistrementV2 {

    public boolean enregistre(Dessin dessin) {
        return enregistreSous(dessin);
    }
    public boolean enregistreSous(Dessin dessin) {
        try {
            DataOutputStream dos;
            dos = new DataOutputStream(new FileOutputStream(dessin.getFichier()));
            dos.writeByte(2);
            enregistreDans(dos, dessin);
            dos.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    private void enregistreDans(DataOutputStream dos, Dessin dessin) throws Exception {
        List<Figure> contenu = dessin.getContenu();
        dos.writeInt(contenu.size());
        for (Figure fig : contenu) {
            fig.enregistreDans(dos);
        }
        dessin.setModifie(false);
    }
}
