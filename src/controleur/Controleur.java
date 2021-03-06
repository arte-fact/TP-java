package controleur;

import javafx.scene.Cursor;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.Dessin;
import modele.OuvertureDessin;
import outils.JDOM;
import vue.APropos;
import vue.PanneauDeDessin;

import java.io.File;

import static vue.Dialogues.confirmation;
import static vue.Dialogues.erreur;

public class Controleur {
	private PanneauDeDessin panneau;
	private EcouteurSouris outil, crayon, etoile;
	private EcouteurClavier ecouteurClavier;
	private Dessin dessin;
	private Stage fenetre;
	private JDOM jdom;

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

		try {
			jdom = JDOM.getInstance();
			chargeParametres();
		} catch (Exception e) {
			panneau.changeCouleur(Color.BLACK, "noir");
			panneau.changeEpaisseur(1);
			panneau.miseAJourTitre();
		}
	}

    public void chargeParametres() {

        String outil = jdom.getValue("outil");
        String couleur = jdom.getValue("couleur");
        String epaisseur = jdom.getValue("epaisseur");

		switch (outil) {
			case "Crayon": panneau.outilCrayon();
			case "Etoile": panneau.outilEtoile();
		}

		switch (couleur) {
			case "noir": panneau.changeCouleur(Color.BLACK, couleur);
			case "rouge": panneau.changeCouleur(Color.RED, couleur);
			case "bleu": panneau.changeCouleur(Color.BLUE, couleur);
			case "jaune": panneau.changeCouleur(Color.YELLOW, couleur);
			case "vert": panneau.changeCouleur(Color.GREEN, couleur);
		}

		panneau.changeEpaisseur(Integer.parseInt(epaisseur));
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
		    		erreur("Ouverture de dessin", "La version de ce fichier n'est pas reconnue par cette application.\nLe dessin n'a pas été chargé !");
		    		break;
		    	case PROBLEME_PENDANT_LECTURE :
		    		erreur("Ouverture de dessin", "Une erreur s'est produite pendant l'ouverture du dessin.\nLe dessin n'a pas été chargé !");
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
		    	erreur("Enregistrement de dessin", "Une erreur s'est produite pendant l'enregistrement du dessin.\nLe dessin n'a pas été enregistré !");
		    }
		}
		return ok;
	}
	public boolean enregistrer() {
		boolean ok = dessin.enregistre(dessin.getFichier());
	    if (ok) {
	        panneau.miseAJourTitre();
	    } else {
	    	erreur("Enregistrement de dessin", "Une erreur s'est produite pendant l'enregistrement du dessin.\nLe dessin n'a pas été enregistré !");
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
