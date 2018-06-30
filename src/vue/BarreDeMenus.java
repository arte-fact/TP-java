package vue;

import controleur.Controleur;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.paint.Color;

public class BarreDeMenus extends MenuBar {
	private Controleur ctrl;
	private MenuItem optionEnregistrer;
	private RadioMenuItem choixCrayon, choixEtoile;
	void controleur(Controleur c) {
		ctrl = c;
	}
	BarreDeMenus() {
		super();
		Menu m = ajouteMenu("Gribouille");
		ajouteElement("A propos", m).setOnAction(evt -> ctrl.aPropos());
		ajouteSeparateur(m);
		ajouteElement("Quitter", m).setOnAction(evt -> ctrl.quitter());
		m = ajouteMenu("Dessin");
		ajouteElement("Effacer", m).setOnAction(evt -> ctrl.effacer());
		ajouteSeparateur(m);
		ajouteElement("Ouvrir...", m).setOnAction(evt -> ctrl.ouvrir());
		optionEnregistrer = ajouteElement("Enregistrer", m);
		optionEnregistrer.setOnAction(evt -> ctrl.enregistrer());
		optionEnregistrer.setDisable(true);
		ajouteElement("Enregistrer sous...", m).setOnAction(evt -> ctrl.enregistrerSous());
		m = ajouteMenu("Outil");
		ToggleGroup outils = new ToggleGroup();
		choixCrayon = ajouteRadioElement("Crayon", m, outils);
		choixCrayon.setAccelerator(new KeyCodeCombination(KeyCode.C));
		choixCrayon.setOnAction(evt -> ctrl.outilCrayon());
		choixCrayon.setSelected(true);
		choixEtoile = ajouteRadioElement("Etoile", m, outils);
		choixEtoile.setAccelerator(new KeyCodeCombination(KeyCode.E));
		choixEtoile.setOnAction(evt -> ctrl.outilEtoile());
		m = ajouteMenu("Couleur");
		ToggleGroup groupeCouleurs = new ToggleGroup();
		RadioMenuItem rmi;
		rmi = ajouteRadioElement("Noir",  m, groupeCouleurs);
		rmi.setOnAction(evt -> ctrl.changeCouleur(Color.BLACK, "noir"));
		rmi.setAccelerator(new KeyCodeCombination(KeyCode.N));
		rmi.setSelected(true);
		rmi = ajouteRadioElement("Rouge", m, groupeCouleurs);
		rmi.setAccelerator(new KeyCodeCombination(KeyCode.R));
		rmi.setOnAction(evt -> ctrl.changeCouleur(Color.RED, "rouge"));
		rmi = ajouteRadioElement("Vert",  m, groupeCouleurs);
		rmi.setAccelerator(new KeyCodeCombination(KeyCode.V));
		rmi.setOnAction(evt -> ctrl.changeCouleur(Color.GREEN, "vert"));
		rmi = ajouteRadioElement("Bleu",  m, groupeCouleurs);
		rmi.setAccelerator(new KeyCodeCombination(KeyCode.B));
		rmi.setOnAction(evt -> ctrl.changeCouleur(Color.BLUE, "bleu"));
		rmi = ajouteRadioElement("Jaune", m, groupeCouleurs);
		rmi.setAccelerator(new KeyCodeCombination(KeyCode.J));
		rmi.setOnAction(evt -> ctrl.changeCouleur(Color.YELLOW, "jaune"));
	}
	private Menu ajouteMenu(String nom) {
		Menu m = new Menu(nom);
		getMenus().add(m);
		return m;
	}
	private MenuItem ajouteElement(String nom, Menu m) {
		MenuItem mi = new MenuItem(nom);
		m.getItems().add(mi);
		return mi;
	}
	private RadioMenuItem ajouteRadioElement(String nom, Menu m, ToggleGroup g) {
		RadioMenuItem mi = new RadioMenuItem(nom);
		m.getItems().add(mi);
		mi.setToggleGroup(g);
		return mi;
	}
	private void ajouteSeparateur(Menu m) {
		SeparatorMenuItem s = new SeparatorMenuItem();
		m.getItems().add(s);
	}
	void selectionneCrayon() {
		choixCrayon.setSelected(true);
	}
	void selectionneEtoile() {
		choixEtoile.setSelected(true);
	}
	void activeEnregistrer() {
		optionEnregistrer.setDisable(false);
	}
	public void desactiveEnregistrer() {
		optionEnregistrer.setDisable(true);
	}
}
