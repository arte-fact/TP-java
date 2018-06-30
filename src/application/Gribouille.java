package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Chargement;
import controleur.Controleur;
import modele.Dessin;
import modele.Enregistrement;
import vue.PanneauDeDessin;

public class Gribouille extends Application {
	public static final String FIN_NOM = " - Gribouille";
    public static void main(String[] args) {
        Application.launch(args);
    }
    public void start(Stage stage) {
        Dessin d = new Dessin();
        Chargement c = new Chargement(d);
        Enregistrement e = new Enregistrement(d);
        PanneauDeDessin pan = new PanneauDeDessin(c, d, stage);
		Controleur ctrl = new Controleur(c, e, d, pan, stage);
		pan.controleur(ctrl);
        Scene scene = new Scene(pan);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setMinWidth(450);
        stage.setMinHeight(300);
        stage.show();
    }
}
