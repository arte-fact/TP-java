package controleur;

import static vue.Dialogues.*;
import java.io.File;

import Factory.FabriqueEnregistrementDessin;
import javafx.scene.Cursor;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;
import modele.ChargementV1;
import modele.Dessin;
import modele.EnregistrementV1;
import modele.OuvertureDessin;
import vue.APropos;
import vue.PanneauDeDessin;

public class Controleur {
	private PanneauDeDessin panneau;
	private EcouteurSouris outil, crayon, etoile;
	private EcouteurClavier ecouteurClavier;
	private Dessin dessin;
	private Stage fenetre;
	public Controleur(Dessin d, PanneauDeDessin p, Stage f) {
		dessin = d;
		panneau = p;
		fenetre = f;
		crayon = new EcouteurTrace(dessin, panneau);
		etoile = new EcouteurEtoile(dessin, panneau);
		ecouteurClavier = new EcouteurClavier(this);
		fenetre.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> ecouteurClavier.onKeyPressed(evt));
		panneau.controleur(this);
		panneau.zoneDeDessin().setOnMouseMoved((MouseEvent evt) -> outil.onMouseMoved(evt));
		panneau.zoneDeDessin().setOnMousePressed((MouseEvent evt) -> outil.onMousePressed(evt));
		panneau.zoneDeDessin().setOnMouseDragged((MouseEvent evt) -> outil.onMouseDragged(evt));
        panneau.zoneDeDessin().setOnMouseEntered((MouseEvent evt) -> fenetre.getScene().setCursor(Cursor.HAND));
        panneau.zoneDeDessin().setOnMouseExited((MouseEvent evt) -> fenetre.getScene().setCursor(Cursor.DEFAULT));
        fenetre.setOnCloseRequest((WindowEvent evt) -> onClose(evt));
		panneau.afficheCoordonnees(0, 0);
		outilCrayon();
		panneau.changeCouleur(Color.BLACK, "noir");
		panneau.changeEpaisseur(1);
		panneau.miseAJourTitre();
	}
	public void aPropos() {
		APropos.unAPropos().show();
	}
	public void outilCrayon() {
		outil = crayon;
		panneau.outilCrayon();
	}
	public void outilEtoile() {
		outil = etoile;
		panneau.outilEtoile();
	}
	public void changeCouleur(Color couleur, String nomCouleur) {
		panneau.changeCouleur(couleur, nomCouleur);
	}
	public void changeEpaisseur(int e) {
		panneau.changeEpaisseur(e);
	}
	public void effacer() {
    	if (confirmationAvecEnregistrementEventuel("Effacer le dessin")) {
            dessin.vider();
            panneau.miseAJourTitre();
            panneau.dessineContenu();
    	}
	}
	public void quitter() {
		if (confirmationAvecEnregistrementEventuel("Quitter Gribouille")) {
			System.exit(0);
		}
	}
	public void ouvrir() {
		FileChooser dialogue = new FileChooser();
		dialogue.getExtensionFilters().addAll(new ExtensionFilter("Fichiers dessin de Gribouille", "*.grb"));
		File fichierChoisi = dialogue.showOpenDialog(null);


		if (fichierChoisi != null) {
	    	dessin.vider();
	    	OuvertureDessin result = dessin.charge(fichierChoisi);
	    	switch (result) {
		    	case REUSSIE :
			    	panneau.activeEnregistrer();
			    	break;
		    	case VERSION_INCONNUE :
		    		erreur("Chargement de dessin", "La version de ce fichier n'est pas reconnue par cette application.\nLe dessin n'a pas �t� charg� !");
		    		break;
		    	case PROBLEME_PENDANT_LECTURE :
		    		erreur("Chargement de dessin", "Une erreur s'est produite pendant l'ouverture du dessin.\nLe dessin n'a pas �t� charg� !");
		    		break;
		    }
	        panneau.miseAJourTitre();
		    panneau.dessineContenu();
		}
	}
	public boolean enregistrerSous() {
		boolean ok = false;
		FileChooser dialogue = new FileChooser();
		dialogue.getExtensionFilters().addAll(new ExtensionFilter("Fichiers dessin de Gribouille", "*.grb"));
		File fichierChoisi = dialogue.showSaveDialog(null);
		if (fichierChoisi != null) {
			ok = dessin.enregistre(fichierChoisi);
		    if (ok) {
		    	panneau.activeEnregistrer();
		        panneau.miseAJourTitre();
		    } else {
		    	erreur("Enregistrement de dessin", "Une erreur s'est produite pendant l'enregistrement du dessin.\nLe dessin n'a pas �t� enregistr� !");
		    }
		}
		return ok;
	}
	public boolean enregistrer() {
		boolean ok = dessin.enregistre(dessin.getFichier());
	    if (ok) {
	        panneau.miseAJourTitre();
	    } else {
	    	erreur("Enregistrement de dessin", "Une erreur s'est produite pendant l'enregistrement du dessin.\nLe dessin n'a pas �t� enregistr� !");
	    }
	    return ok;
	}
    private boolean confirmationAvecEnregistrementEventuel(String nomOp) {
        if (dessin.modifie()) {
            switch (confirmation(nomOp)) {
	            case CONFIRMATION_AVEC_ENREGISTREMENT :
	            	if (dessin.enFichier()) {
            			if (!enregistrer()) return false;
	            	} else {
	            		if (!enregistrerSous()) return false;
	            	}
	            case CONFIRMATION_SANS_ENREGISTREMENT :
	            	return true;
	            case ABANDON :
	            	return false;
            }
        }
        return true;
    }
    private void onClose(WindowEvent e) {
		if (confirmationAvecEnregistrementEventuel("Fermer Gribouille")) {
			System.exit(0);
		}
    }
}
