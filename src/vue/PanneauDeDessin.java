package vue;

import application.Gribouille;
import controleur.Controleur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Chargement;
import modele.Dessin;

public class PanneauDeDessin extends BorderPane {
	private ZoneDeDessin zD;
	private BarreDEtat bE;
	private BarreDeMenus bM;
	private Dessin dessin;
	private  Chargement chargement;
	private Stage fenetre;
	public PanneauDeDessin(Chargement c, Dessin d, Stage s) {
		super();
		dessin = d;
		chargement = c;
		fenetre = s;
		bM = new BarreDeMenus();
		setTop(bM);
		zD = new ZoneDeDessin(d);
		Pane p = new Pane(zD);
		setCenter(p);
        zD.widthProperty().bind(p.widthProperty());
        zD.heightProperty().bind(p.heightProperty());
        zD.widthProperty().addListener(evt -> zD.dessineContenu());
        zD.heightProperty().addListener(evt -> zD.dessineContenu());
		bE = new BarreDEtat();
		setBottom(bE);
		changeCouleur(Color.BLACK, "noir");
		changeEpaisseur(1);
	}
	public void controleur(Controleur c) {
		bM.controleur(c);
	}
	public ZoneDeDessin zoneDeDessin() {
		return zD;
	}
	public void changeCouleur(Color c, String nom) {
		zD.changeCouleur(c);
		bE.afficheCouleur(nom);
	}
	public Color couleur() {
		return zD.couleur();
	}
	public void changeEpaisseur(int e) {
		zD.changeEpaisseur(e);
		bE.afficheEpaisseur(e);
	}
	public int epaisseur() {
		return zD.epaisseur();
	}
	public void outilCrayon() {
		bM.selectionneCrayon();
		bE.afficheOutil("crayon");
	}
	public void outilEtoile() {
		bM.selectionneEtoile();
		bE.afficheOutil("�toile");
	}
	public void dessineTrait(double x1, double y1, double x2, double y2) {
		zD.dessineTrait(x1, y1, x2, y2);
	}
	public void afficheCoordonnees(double x, double y) {
		bE.afficheCoordonnees(x, y);
	}
	public void dessineContenu() {
		zD.dessineContenu();
	}
	public void activeEnregistrer() {
		bM.activeEnregistrer();
	}
	public void miseAJourTitre() {
		fenetre.setTitle(chargement.nomDeFichier() + Gribouille.FIN_NOM);
	}
}
