package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Dessin;
import controleur.Controleur;
import vue.PanneauDeDessin;

public class Gribouille extends Application {
	public static final String FIN_NOM = " - Gribouille";
    public static void main(String[] args) {
        Application.launch(args);
    }
    public void start(Stage stage) {
    	Dessin d = new Dessin();
        PanneauDeDessin pan = new PanneauDeDessin(d, stage);
		Controleur ctrl = new Controleur(d, pan, stage);
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
